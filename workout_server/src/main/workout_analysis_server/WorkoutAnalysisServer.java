package main.workout_analysis_server;


import common.BufferHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class WorkoutAnalysisServer{
    static final int CAPACITY = 100;
    static final int PORT = 8081;

    private static ArrayBlockingQueue<String> FRAME_REQUESTS;

    public WorkoutAnalysisServer() {
        FRAME_REQUESTS = new ArrayBlockingQueue<String>(CAPACITY);
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server Started\n Listening for connections on port: " + PORT);

            Socket apiFrontendSocket = serverSocket.accept();
            System.out.println("Workout Server Connection Accepted! " + apiFrontendSocket.toString());

            BufferedReader input;
            BufferedOutputStream dataOut = null;

            input = new BufferedReader(new InputStreamReader(apiFrontendSocket.getInputStream()));
            dataOut = new BufferedOutputStream(apiFrontendSocket.getOutputStream());

            while(true) {
                //Wait for new request
                while (!input.ready()) {
                    continue;
                }

                System.out.println("Workout Server Received Request: " + BufferHandler.readAllLines(input));
            }

        } catch (IOException ioe) {
            System.err.println("Workout Analysis Server Connection Error: " + ioe.getMessage());
        }
    }
}
