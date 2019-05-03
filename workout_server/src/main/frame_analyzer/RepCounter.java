package frame_analyzer;

import body_data.BodyDataSet;
import common.SlidingList;

public abstract class RepCounter {
    protected SlidingList<BodyDataSet> bodyDataList;
    Exercise exercise;

    public RepCounter(Exercise exercise) {
        this.bodyDataList = new SlidingList<>();
        this.exercise = exercise;
    }

    abstract public boolean analyze(BodyDataSet bodyDataSet);

    abstract public boolean newRep();
}
