package main.frame_analyzer.form_correctors;

import main.analyses.FormAnalysis;
import main.body_data.BodyDataSet;
import main.frame_analyzer.Exercise;
import main.frame_analyzer.FormCorrector;

public class PlankFormCorrector extends FormCorrector {

    private static int TARGET_HIP_ANGLE = 180;
    private static int LENIENCY = 10;

    public PlankFormCorrector() {
        super(Exercise.PLANK);
    }

    @Override
    public FormAnalysis analyze(BodyDataSet bodyDataSet) {
        FormAnalysis FA = new FormAnalysis(Excersize.PLANK);
        hipAngle = bodyDataSet.getHipAngle();
        if( Math.abs(hipAngle) < TARGET_HIP_ANGLE - LENIENCY ) {
            if (hipAngle > 0) {
                FA.addNotes("Hips too low");
            } else {
                FA.addNotes("Hips too high");
            }
        }
        return FA;
    }

    @Override
    public FormAnalysis newRep() {


        return null;
    }
}
