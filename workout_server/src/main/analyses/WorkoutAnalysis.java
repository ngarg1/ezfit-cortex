package main.analyses;

import java.util.ArrayList;

public class WorkoutAnalysis {
    private ArrayList<SetAnalysis> setAnalyses;

    public WorkoutAnalysis() {
        setAnalyses = new ArrayList<>();
    }

    public ArrayList<SetAnalysis> getSetAnalyses() {
        return setAnalyses;
    }

    public void addSetAnalysis(SetAnalysis setAnalysis) {
        setAnalyses.add(setAnalysis);
    }
}
