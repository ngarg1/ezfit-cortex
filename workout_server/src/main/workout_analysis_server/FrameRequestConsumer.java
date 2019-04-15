package main.workout_analysis_server;

public class FrameRequestConsumer implements Runnable {
    private WorkoutAnalysisServer workoutAnalysisServer;

    public FrameRequestConsumer(WorkoutAnalysisServer workoutAnalysisServer) {
        this.workoutAnalysisServer = workoutAnalysisServer;
    }

    @Override
    public void run() {
        while(true) {
            String request = workoutAnalysisServer.getRequest();
            System.out.println("Consuming Request: " + request);
        }
    }
}
