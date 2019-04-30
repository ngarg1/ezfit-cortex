package frame_analyzer.rep_counters;

import body_data.BodyDataSet;
import frame_analyzer.Exercise;
import frame_analyzer.RepCounter;

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
