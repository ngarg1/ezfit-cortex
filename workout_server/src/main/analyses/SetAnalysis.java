package main.analyses;

import main.frame_analyzer.Exercise;

import java.util.ArrayList;

public class SetAnalysis {
    private Exercise exercise;
    private ArrayList<FormAnalysis> formAnalyses;

    public SetAnalysis(Exercise exercise) {
        this.exercise = exercise;
        formAnalyses = new ArrayList<>();
    }

    public Exercise getExercise() {
        return exercise;
    }

    public ArrayList<FormAnalysis> getFormAnalyses() {
        return formAnalyses;
    }

    public void addFormAnalysis(FormAnalysis formAnalysis) {
        formAnalyses.add(formAnalysis);
    }
}
