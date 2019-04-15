package main.api_frontend;


import common.BufferHandler;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class CoreRequestHandler {
    static final String CONNECT_REQUEST = "connect";
    static final String INITIATED_REQUEST = "workout-initiated";
    static final String START_REQUEST = "start";


    public static String handle(String uri,
                                BufferedReader input,
                                AtomicBoolean workoutInitiated,
                                AtomicBoolean piConnected
                                ) throws IOException {

        String strippedUri = uri.replace("/core/", "");
        String response;

        switch (strippedUri) {
            case CONNECT_REQUEST:
                response = connectRequest(piConnected);
                break;
            case INITIATED_REQUEST:
                response = initiatedRequest(workoutInitiated);
                break;
            case START_REQUEST:
                response = startRequest(workoutInitiated);
                break;
            default:
                response = getSample200Reponse("Core Request -- Unhandled Request");
                break;

        }
        BufferHandler.readAllLines(input);  // Clear input buffer

        return response;
    }

    public static String connectRequest(AtomicBoolean piConnected) {
        System.out.println("Connect Request Handler");
        piConnected.set(true);
        return getSample200Reponse("Raspberry Pi Connected");
    }

    public static String startRequest(AtomicBoolean workoutInitiated) {
        System.out.println("Start Workout Request Handler");
        workoutInitiated.set(true);
        return getSample200Reponse("Workout Started");
    }

    public static String initiatedRequest(AtomicBoolean workoutInitiated) {
        System.out.println("Initiated Request Handler");
        return getSample200Reponse("Workout Initiated: " + workoutInitiated.get());
    }

    public static String getSample200Reponse(String identifier) {
        String body = "<html>\n" +
                "<head>\n" +
                "   <title>200 OK</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "   <h1>" + identifier + "</h1>\n" +
                "   <p>18-500\n" +
                "</body>\n" +
                "</html>";
        StringBuilder sampleResponse = new StringBuilder();
        sampleResponse.append("HTTP/1.1 200 OK\r\n");
        sampleResponse.append("Date: Mon, 27 Jul 2009 12:28:53 GMT\r\n");
        sampleResponse.append("Content-Type: text/html\r\n");
        sampleResponse.append("Connection: keep-alive\r\n");
        sampleResponse.append("Content-Length: ");
        sampleResponse.append(body.getBytes().length);
        sampleResponse.append("\r\n\r\n");
        sampleResponse.append(body);

        return sampleResponse.toString();
    }
}
