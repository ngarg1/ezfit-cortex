package frame_analyzer.form_correctors;

import analyses.FormAnalysis;
import body_data.BodyDataSet;
import frame_analyzer.Exercise;
import frame_analyzer.FormCorrector;


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
                FA.addNotes("Yo butt too low");
            } else {
                FA.addNotes("Yo butt too high");
            }
        }else{
            FA.addNotes("Lookin good big boy")
        }
        return FA;
    }

    @Override
    public FormAnalysis newRep() {


        return null;
    }
}
