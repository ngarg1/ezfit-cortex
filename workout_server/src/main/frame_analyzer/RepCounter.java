package frame_analyzer;

import common.SlidingList;
import body_data.BodyDataSet;

public abstract class RepCounter {
    SlidingList<BodyDataSet> bodyDataList;
    Exercise exercise;

    public RepCounter(Exercise exercise) {
        this.bodyDataList = new SlidingList<>();
        this.exercise = exercise;
    }

    abstract public boolean analyze(BodyDataSet bodyDataSet);

    abstract public boolean newRep();
}
