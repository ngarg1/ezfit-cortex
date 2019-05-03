package frame_analyzer.form_correctors;

import analyses.FormAnalysis;
import body_data.BodyDataPoint;
import body_data.BodyDataSet;
import frame_analyzer.Exercise;
import frame_analyzer.FormCorrector;


public class SitupFormCorrector extends FormCorrector {
    public SitupFormCorrector() {
        super(Exercise.SITUP);
    }

    private static int LENIENCY = 10;

    @Override
    public analyses.FormAnalysis analyze(BodyDataSet bodyDataSet) {

        FormAnalysis FA = new FormAnalysis(Exercise.PLANK);
        BodyDataPoint shoulder = bodyDataSet.getShoulder();
        BodyDataPoint knee = bodyDataSet.getKnee();

        if( shoulder.getY() < knee.getY()+LENIENCY ){
            FA.addNotes("All the way up! Good Job!");
        }
        return FA;
    }

    @Override
    public FormAnalysis newRep() {
        return null;
    }
}
