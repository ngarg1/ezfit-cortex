package main.workout_analysis_server;


import common.BufferHandler;
import common.SlidingList;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class WorkoutAnalysisServer implements Runnable {
    static final int CAPACITY = 100;
    static final int PORT = 8081;

    private static ArrayBlockingQueue<String> frameRequests;

    public WorkoutAnalysisServer() {
        frameRequests = new ArrayBlockingQueue<String>(CAPACITY);
    }

    public static void main(String[] args) {
        WorkoutAnalysisServer workoutAnalysisServer = new WorkoutAnalysisServer();
        Thread workoutServerThread = new Thread(workoutAnalysisServer);
        System.out.println("Starting Workout Analysis Server");
        workoutServerThread.start();

        FrameRequestConsumer frameRequestConsumer = new FrameRequestConsumer(workoutAnalysisServer);
        Thread frameRequestConsumerThread = new Thread(frameRequestConsumer);
        System.out.println("Starting Frame Analysis Server");
        frameRequestConsumerThread.start();

        SlidingList<Integer> integerSlidingList = new SlidingList<>(5);
        integerSlidingList.add(1);
        System.out.println(integerSlidingList.getList());
        integerSlidingList.add(2);
        System.out.println(integerSlidingList.getList());
        integerSlidingList.add(3);
        System.out.println(integerSlidingList.getList());
        integerSlidingList.add(4);
        System.out.println(integerSlidingList.getList());
        integerSlidingList.add(5);
        System.out.println(integerSlidingList.getList());
        integerSlidingList.add(6);
        System.out.println(integerSlidingList.getList());
        integerSlidingList.add(7);
        System.out.println(integerSlidingList.getList());
        System.out.println(integerSlidingList.countList());
        integerSlidingList.add(6);
        System.out.println(integerSlidingList.countList());

    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server Started\n Listening for connections on port: " + PORT);

            Socket apiFrontendSocket = serverSocket.accept();
            System.out.println("Workout Server Connection Accepted! " + apiFrontendSocket.toString());

            BufferedReader input;
            BufferedOutputStream dataOut = null;

            input = new BufferedReader(new InputStreamReader(apiFrontendSocket.getInputStream()));
            dataOut = new BufferedOutputStream(apiFrontendSocket.getOutputStream());

            String request;
            while(true) {
                while (!input.ready()) {
                    continue;
                }

                request = BufferHandler.readAllLines(input);
                System.out.println("Workout Server Received Request: " + request);

                try {
                    frameRequests.put(request);
                } catch (InterruptedException ie) {
                    System.err.println("Workout Server interrupted while queueing request: " + ie.getMessage());
                }
                System.out.println("Request Queue: " + frameRequests.toString());
                BufferHandler.readAllLines(input);
            }

        } catch (IOException ioe) {
            System.out.println("Workout Analysis Server Connection Error: " + ioe.getMessage());
        }
    }

    public String getRequest() {
        try {
            System.out.println("Taking from QUEUE");
            return frameRequests.take();
        } catch (InterruptedException ie) {
            System.err.println("Workout Server interrupted while trying to provide request to consume: "
                    + ie.getMessage());
        }
        return null;
    }
}
