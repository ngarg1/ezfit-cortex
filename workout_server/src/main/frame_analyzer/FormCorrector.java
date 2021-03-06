package frame_analyzer;

import analyses.FormAnalysis;
import common.SlidingList;
import body_data.BodyDataSet;

public abstract class FormCorrector {
    FormAnalysis currentRepForm;
    SlidingList<BodyDataSet> bodyDataList;
    Exercise exercise;

    public FormCorrector(Exercise exercise) {
        bodyDataList = new SlidingList();
        currentRepForm = new FormAnalysis(exercise);
        this.exercise = exercise;
    }

    abstract public FormAnalysis analyze(BodyDataSet bodyDataSet);

    abstract public FormAnalysis newRep();
}
