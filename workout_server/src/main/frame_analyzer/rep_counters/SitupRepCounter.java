package frame_analyzer.rep_counters;

import body_data.BodyDataSet;
import body_data.BodyDataPoint;
import common.SlidingList;
import frame_analyzer.Exercise;
import frame_analyzer.RepCounter;

public class SitupRepCounter extends RepCounter {
    public SitupRepCounter() {
        super(Exercise.SITUP);
    }


    private double DELTA;
    private boolean SWITCH;

    @Override
    public boolean analyze(BodyDataSet bodyDataSet) {
        if(bodyDataList.getSize() == 0){
            bodyDataList.add(bodyDataSet);
            return false;
        }

        BodyDataPoint prevShoulder = bodyDataList.getLast().getShoulder();
        double delta_new = bodyDataSet.getShoulder().getY() - prevShoulder.getY();

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
