package main.frame_analyzer.rep_counters;

import main.body_data.BodyDataSet;
import main.frame_analyzer.Exercise;
import main.frame_analyzer.RepCounter;

public class SquatRepCounter extends RepCounter {
    public SquatRepCounter() {
        super(Exercise.SQUAT);
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
