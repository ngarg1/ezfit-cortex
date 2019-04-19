package main.workout_analysis_server;

import common.SlidingList;
import main.analyses.SetAnalysis;
import main.analyses.WorkoutAnalysis;
import main.body_data.BodyDataSet;
import main.body_data.BodyDataSetSerializer;
import main.classifier.ClassifierHandler;
import main.frame_analyzer.Exercise;
import main.sets.SetStateDecider;

public class FrameRequestConsumer implements Runnable {
    static final int EXERCISE_LIST_CAPACITY = 5;

    private WorkoutAnalysisServer workoutAnalysisServer;

    public FrameRequestConsumer(WorkoutAnalysisServer workoutAnalysisServer) {
        this.workoutAnalysisServer = workoutAnalysisServer;
    }

    @Override
    public void run() {
        Exercise frameExercise = null;
        Exercise currentExercise = null;
        SetAnalysis currentSetAnalysis;
        BodyDataSet bodyDataSet;

        SlidingList<Exercise> exerciseList = new SlidingList<>(EXERCISE_LIST_CAPACITY);
        ClassifierHandler classifierHandler = new ClassifierHandler();
        WorkoutAnalysis workoutAnalysis = new WorkoutAnalysis();

        while(true) {
            String request = workoutAnalysisServer.getRequest();
            System.out.println("Consuming Request: " + request);

            bodyDataSet = BodyDataSetSerializer.deserializeFrameRequest(request);
            frameExercise = classifierHandler.classify(bodyDataSet);
            exerciseList.add(frameExercise);

            switch(SetStateDecider.getSetState(currentExercise, exerciseList)) {
                case INITIAL:
                    currentExercise = frameExercise;
                    currentSetAnalysis = new SetAnalysis(currentExercise);
                    break;
                case NEW_SET:
                    //TBI
                    break;
                case SAME_SET:
                    //TBI
                    break;
                case UNKNOWN:
                    break;
            }
        }
    }
}
