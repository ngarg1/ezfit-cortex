package frame_analyzer.rep_counters;

import body_data.BodyDataSet;
import frame_analyzer.Exercise;
import frame_analyzer.RepCounter;

public class SitupRepCounter extends RepCounter {
    public SitupRepCounter() {
        super(Exercise.SITUP);
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
