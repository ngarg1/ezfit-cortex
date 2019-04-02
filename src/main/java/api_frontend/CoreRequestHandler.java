package api_frontend;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;

public class CoreRequestHandler {
    static final String CONNECT_REQUEST = "connect";
    static final String INITIATED_REQUEST = "workout-initiated";

    public static String handle(String uri, BufferedReader input, BufferedOutputStream output) throws IOException {
        String strippedUri = uri.replace("/core/", "");
        String response;

        switch (strippedUri) {
            case CONNECT_REQUEST:
                response = connectRequest(input);
                break;
            case INITIATED_REQUEST:
                response = initiatedRequest(input);
                break;
            default:
                response = "Unsupported Request";
                break;

        }
        BufferHandler.readAllLines(input);  // Clear input buffer

        response = getSample200Reponse("Core Request"); //TEMPORARY
        return response;
    }

    public static String connectRequest(BufferedReader input) {
        //TO BE IMPLEMENTED
        return "Connect Request Handler";
    }

    public static String initiatedRequest(BufferedReader input) {
        //TO BE IMPLEMENTED
        return "Initiated Request Handler";
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
        sampleResponse.append("Connection: close\r\n");
        sampleResponse.append("Content-Length: ");
        sampleResponse.append(body.getBytes().length);
        sampleResponse.append("\r\n\r\n");
        sampleResponse.append(body);

        return sampleResponse.toString();
    }
}
