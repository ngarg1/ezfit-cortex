package main.frame_analyzer;

import main.analyses.FormAnalysis;
import main.analyses.FrameAnalysis;
import main.body_data.BodyDataSet;
import main.frame_analyzer.form_correctors.PlankFormCorrector;
import main.frame_analyzer.form_correctors.SitupFormCorrector;
import main.frame_analyzer.form_correctors.SquatFormCorrector;
import main.frame_analyzer.rep_counters.PlankRepCounter;
import main.frame_analyzer.rep_counters.SitupRepCounter;
import main.frame_analyzer.rep_counters.SquatRepCounter;

public class FrameAnalyzer {
    private FormCorrector formCorrector;
    private RepCounter repCounter;

    public FrameAnalyzer(Exercise exercise) {
        switch(exercise) {
            case SQUAT:
                formCorrector = new SquatFormCorrector();
                repCounter = new SquatRepCounter();
                break;
            case SITUP:
                formCorrector = new SitupFormCorrector();
                repCounter = new SitupRepCounter();
                break;
            case PLANK:
                formCorrector = new PlankFormCorrector();
                repCounter = new PlankRepCounter();
        }
    }

    public FrameAnalysis analysis(BodyDataSet bodyDataSet) {
        FormAnalysis formAnalysis = formCorrector.analyze(bodyDataSet);
        boolean isNewRep = repCounter.analyze(bodyDataSet);
        return new FrameAnalysis(formAnalysis, isNewRep);
    }

    public FormAnalysis newRep() {
        repCounter.newRep();
        return formCorrector.newRep();
    }
}
