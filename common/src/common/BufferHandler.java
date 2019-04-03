package common;

import java.io.BufferedReader;
import java.io.IOException;

public class BufferHandler {
    public static String readAllLines(BufferedReader buffer) {
        StringBuilder bufferStringReader = new StringBuilder();
        String line;
        try {
            while(buffer.ready() && (line = buffer.readLine()) != null) {
                bufferStringReader.append(line + "\n");
            }
        } catch (IOException ioe) {
            System.out.println("Error reading buffer" + ioe.getMessage());
            return null;
        }
        return bufferStringReader.toString();
    }
}
