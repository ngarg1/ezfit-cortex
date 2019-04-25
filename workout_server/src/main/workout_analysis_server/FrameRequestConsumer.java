package main.workout_analysis_server;

import common.SlidingList;
import main.analyses.FrameAnalysis;
import main.analyses.SetAnalysis;
import main.analyses.WorkoutAnalysis;
import main.body_data.BodyDataSet;
import main.body_data.BodyDataSetSerializer;
import main.classifier.ClassifierHandler;
import main.frame_analyzer.Exercise;
import main.frame_analyzer.FrameAnalyzer;
import main.frontend_handler.FrontendHandler;
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
        FrameAnalyzer frameAnalyzer = null;
        SetAnalysis currentSetAnalysis = null;
        BodyDataSet bodyDataSet;
        FrameAnalysis frameAnalysis;

        SlidingList<Exercise> exerciseList = new SlidingList<>(EXERCISE_LIST_CAPACITY);
        ClassifierHandler classifierHandler = new ClassifierHandler();
        WorkoutAnalysis workoutAnalysis = new WorkoutAnalysis();

        FrontendHandler frontendHandler = new FrontendHandler();

        while(true) {
            String request = workoutAnalysisServer.getRequest();
            System.out.println("Consuming Request: " + request);

            bodyDataSet = BodyDataSetSerializer.deserializeFrameRequest(request);
            frameExercise = classifierHandler.classify(bodyDataSet);
            System.out.println(frameExercise);
            exerciseList.add(frameExercise);

            switch(SetStateDecider.getSetState(currentExercise, exerciseList)) {
                case INITIAL:
                    System.out.println("First Frame Request");

                    currentExercise = frameExercise;
                    currentSetAnalysis = new SetAnalysis(currentExercise);
                    frameAnalyzer = new FrameAnalyzer(currentExercise);

                    frameAnalysis = frameAnalyzer.analyze(bodyDataSet);
                    if(frameAnalysis.isNewRep()) {
                        currentSetAnalysis.addFormAnalysis(frameAnalyzer.newRep());
                    }

                    frontendHandler.sendDisplayRequest(frameExercise);
                    break;
                case SAME_SET:
                    System.out.println("Same Set -- Normal Analysis");

                    frameAnalysis = frameAnalyzer.analyze(bodyDataSet);
                    if(frameAnalysis.isNewRep()) {
                        currentSetAnalysis.addFormAnalysis(frameAnalyzer.newRep());
                    }

                    frontendHandler.sendDisplayRequest(frameExercise);
                    break;
                case NEW_SET:
                    System.out.println("New Set -- Nice job big guy");


                    break;
                case UNKNOWN:
                    System.out.println("Oopsie! Unknown State reached");

                    break;
            }
        }
    }
}
