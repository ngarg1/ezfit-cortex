package frame_analyzer.rep_counters;

import body_data.BodyDataSet;
import body_data.BodyDataPoint;
import common.SlidingList;
import frame_analyzer.Exercise;
import frame_analyzer.RepCounter;

public class SquatRepCounter extends RepCounter {
    public SquatRepCounter() {
        super(Exercise.SQUAT);
    }

    private double DELTA;
    private boolean SWITCH;

    @Override
    public boolean analyze(BodyDataSet bodyDataSet) {
        if(bodyDataList.getSize() == 0){
            bodyDataList.add(bodyDataSet);
            return false;
        }

        BodyDataPoint prevHip = bodyDataList.getLast().getHip();
        double delta_new = bodyDataSet.getHip().getY() - prevHip.getY();
        if( delta_new * DELTA < 0){
            if(SWITCH){
                return true;
            }
            SWITCH = true;
        }
        DELTA = delta_new;
        bodyDataList.add(bodyDataSet);

        return false;
    }

    @Override
    public boolean newRep() {
        DELTA = 0;
        SWITCH = false;
        bodyDataList = new SlidingList<>();
        return false;
    }
}
