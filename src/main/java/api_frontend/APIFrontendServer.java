package api_frontend;

import workout_analysis_server.WorkoutAnalysisServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class APIFrontendServer implements Runnable {
    static final int PORT = 8080;
    static final int WORKOUT_SERVER_PORT = 8081;
    static final boolean verbose = true;
    static final String LOCAL_HOST = "127.0.0.1";
    static final String WORKOUT_SERVER_HOSTNAME = ""; // TO BE DISCOVERED

    static final String CORE_REQUEST = "/core";
    static final String WORKOUT_REQUEST = "/workout";

    private Socket connect;

    private Socket workoutServerSocket;
    private BufferedReader workoutServerInput;
    private BufferedOutputStream workoutServerOutput;

    public APIFrontendServer(Socket connect) {
        this.connect = connect;
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server Started\n Listening for connections on port: " + PORT);

            WorkoutAnalysisServer workoutAnalysisServer = new WorkoutAnalysisServer();

            while (true) {
                APIFrontendServer apiFrontendServer = new APIFrontendServer(serverSocket.accept());

                if(verbose) {
                    System.out.println("Connection Opened -- " + new Date());
                }

                Thread serverThread = new Thread(apiFrontendServer);
                serverThread.start();
            }

        } catch (IOException ioe) {
            System.err.println("Server Connection Error: " + ioe.getMessage());
        }
    }

    @Override
    public void run() {
        BufferedReader input;
        PrintWriter outputHeaders;
        BufferedOutputStream dataOut = null;

        try {
            input = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            outputHeaders = new PrintWriter(connect.getOutputStream());
            dataOut = new BufferedOutputStream(connect.getOutputStream());

            while(true) {
                //Wait for new request
                while(!input.ready()) {
                    continue;
                }

                String request = input.readLine();
                StringTokenizer requestParser = new StringTokenizer(request);

                String requestMethod = requestParser.nextToken().toUpperCase();
                String uri = requestParser.nextToken().toLowerCase();

                String response = "";
                if(uri.startsWith(CORE_REQUEST)) {
                    response = CoreRequestHandler.handle(uri, input, dataOut);
                } else if(uri.startsWith(WORKOUT_REQUEST)) {
                    //Pass off to workout request handler
                    sendToWorkoutServer(request, input);
                    response = CoreRequestHandler.getSample200Reponse("Workout Request"); //TEMPORARY
                }

                dataOut.write(response.getBytes(), 0, response.getBytes().length);
                dataOut.flush();
            }

        } catch (IOException ioe) {
            System.err.println("Input Reading Error: " + ioe.getMessage());
        }
    }

    private boolean sendToWorkoutServer(String requestLine, BufferedReader input) {
        String request;
        System.out.println("Sending to Workout Analysis Server");

        if(workoutServerSocket == null) {
            try {
                workoutServerSocket = new Socket(LOCAL_HOST, WORKOUT_SERVER_PORT);
                workoutServerInput = new BufferedReader(new InputStreamReader(workoutServerSocket.getInputStream()));
                workoutServerOutput = new BufferedOutputStream(workoutServerSocket.getOutputStream());
            } catch (IOException ioe) {
                System.err.println("Failed to connect to Workout Analysis Server" + ioe.getMessage());
                return false;
            }
        }

        request = requestLine + "\n" + BufferHandler.readAllLines(input);
        System.out.println("Byte Array: " + new String(request.getBytes(), StandardCharsets.UTF_8));

        try {
            workoutServerOutput.write(request.getBytes(), 0, request.getBytes().length);
            workoutServerOutput.flush();
        } catch (IOException ioe) {
            System.err.println("Failed to send request to Workout Analysis Server" + ioe.getMessage());
            return false;
        }

        return true;
    }
}
