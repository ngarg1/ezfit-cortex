package frame_analyzer.form_correctors;

import analyses.FormAnalysis;
import body_data.BodyDataSet;
import frame_analyzer.Exercise;
import frame_analyzer.FormCorrector;


public class SquatFormCorrector extends FormCorrector {
    public SquatFormCorrector() {
        super(Exercise.SQUAT);
    }

    private static int KNEE_ANKLE_LENIENCY = 40;
    private static int TARGET_KNEE_ANGLE = 90;

    @Override
    public FormAnalysis analyze(BodyDataSet bodyDataSet) {

        FormAnalysis FA = new FormAnalysis(Exercise.SQUAT);
        double kneePoint = bodyDataSet.getKnee().getX();
        double anklePoint = bodyDataSet.getAnkle().getX();
        double kneeAngle = bodyDataSet.getKneeAngle();

        if(Math.abs(kneePoint - anklePoint) > KNEE_ANKLE_LENIENCY){
            FA.addNotes("Get yo knees ovah yo ankles");
        }
        if(kneeAngle <= TARGET_KNEE_ANGLE){
            FA.addNotes("All the way down! Good!");
        }

        return FA;
    }

    @Override
    public FormAnalysis newRep() {
        return null;
    }
}
