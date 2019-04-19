package main.frame_analyzer.form_correctors;

import main.analyses.FormAnalysis;
import main.body_data.BodyDataSet;
import main.frame_analyzer.Exercise;
import main.frame_analyzer.FormCorrector;

public class SquatFormCorrector extends FormCorrector {
    public SquatFormCorrector() {
        super(Exercise.SQUAT);
    }

    @Override
    public FormAnalysis analyze(BodyDataSet bodyDataSet) {
        return null;
    }

    @Override
    public FormAnalysis newRep() {
        return null;
    }
}
