package main.frame_analyzer.rep_counters;

import common.SlidingList;
import main.body_data.BodyDataSet;
import main.frame_analyzer.Exercise;
import main.frame_analyzer.RepCounter;

public class PlankRepCounter extends RepCounter {
    public PlankRepCounter() {
        super(Exercise.PLANK);
    }

    @Override
    public boolean analyze(BodyDataSet bodyDataSet) {
        return false;
    }

    @Override
    public boolean newRep() {
        return false;
    }
}
