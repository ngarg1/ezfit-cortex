package workout_analysis_server;

import analyses.FrameAnalysis;
import analyses.SetAnalysis;
import analyses.WorkoutAnalysis;
import body_data.BodyDataSet;
import body_data.BodyDataSetSerializer;
import classifier.ClassifierHandler;
import common.SlidingList;
import frame_analyzer.Exercise;
import frame_analyzer.FrameAnalyzer;
import frontend_handler.FrontendHandler;
import sets.SetStateDecider;

import java.util.Map;


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
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            String request = workoutAnalysisServer.getRequest();
            System.out.println("Consuming Request: " + request);

            bodyDataSet = BodyDataSetSerializer.deserializeFrameRequest(request);

            if(bodyDataSet == null) {
                continue;
            }

            frameExercise = classifierHandler.classify(bodyDataSet);
            System.out.println("Exercise: " + frameExercise);

            frontendHandler.sendDisplayRequest(frameExercise);
            //exerciseList.add(frameExercise);


            /*
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

                    Map<Exercise, Integer> countMap = exerciseList.countList();
                    Exercise newExercise = countMap.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

                    workoutAnalysis.addSetAnalysis(currentSetAnalysis);
                    currentSetAnalysis = new SetAnalysis(newExercise);
                    frameAnalyzer = new FrameAnalyzer(newExercise);
                    currentExercise = newExercise;

                    frontendHandler.sendDisplayRequest(frameExercise);
                    break;
                case UNKNOWN:
                    System.out.println("Oopsie! Unknown State reached");

                    break;
            }*/
        }
    }
}
