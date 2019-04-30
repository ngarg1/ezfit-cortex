package frame_analyzer.form_correctors;

import analyses.FormAnalysis;
import body_data.BodyDataSet;
import frame_analyzer.Exercise;
import frame_analyzer.FormCorrector;


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
