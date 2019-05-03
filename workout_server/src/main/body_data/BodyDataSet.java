package body_data;

import java.util.Date;
import java.lang.Math;

public class BodyDataSet {
    private BodyDataPoint nose, neck, shoulder, elbow, wrist, hip, knee, ankle;
    private Date timestamp;

    public BodyDataSet(BodyDataPoint nose,
                       BodyDataPoint neck,
                       BodyDataPoint shoulder,
                       BodyDataPoint elbow,
                       BodyDataPoint wrist,
                       BodyDataPoint hip,
                       BodyDataPoint knee,
                       BodyDataPoint ankle,
                       Date timestamp) {
        this.nose = nose;
        this.neck = neck;
        this.shoulder = shoulder;
        this.elbow = elbow;
        this.wrist = wrist;
        this.hip = hip;
        this.knee = knee;
        this.ankle = ankle;
        this.timestamp = timestamp;
    }

    public BodyDataPoint getNose() { return nose; }

    public BodyDataPoint getNeck() {
        return neck;
    }

    public BodyDataPoint getShoulder() { return shoulder; }

    public BodyDataPoint getElbow() {
        return elbow;
    }

    public BodyDataPoint getWrist() {
        return wrist;
    }

    public BodyDataPoint getHip() {
        return hip;
    }

    public BodyDataPoint getKnee() {
        return knee;
    }

    public BodyDataPoint getAnkle() {
        return ankle;
    }

    public void normalize(){
        double offsetX = hip.getX();
        double offsetY = hip.getY();

        if( ankle.getConfidence() == 0) { ankle = new BodyDataPoint(0,0,0); }
        else {
            ankle = new BodyDataPoint(ankle.getX() - offsetX, ankle.getY() - offsetY, ankle.getConfidence());
        }

        if( knee.getConfidence() == 0) { knee = new BodyDataPoint(0,0,0); }
        else {
            knee = new BodyDataPoint(knee.getX() - offsetX, knee.getY() - offsetY, knee.getConfidence());
        }

        hip = new BodyDataPoint(0, 0, hip.getConfidence());

        if( wrist.getConfidence() == 0) { wrist = new BodyDataPoint(0,0,0); }
        else {
            wrist = new BodyDataPoint(wrist.getX() - offsetX, wrist.getY() - offsetY, wrist.getConfidence());
        }

        if( elbow.getConfidence() == 0) { elbow = new BodyDataPoint(0,0,0); }
        else {
            elbow = new BodyDataPoint(elbow.getX() - offsetX, elbow.getY() - offsetY, elbow.getConfidence());
        }

        if( shoulder.getConfidence() == 0) { shoulder = new BodyDataPoint(0,0,0); }
        else {
            shoulder = new BodyDataPoint(shoulder.getX() - offsetX, shoulder.getY() - offsetY, shoulder.getConfidence());
        }

        if( neck.getConfidence() == 0) { neck = new BodyDataPoint(0,0,0); }
        else {
            neck = new BodyDataPoint(neck.getX() - offsetX, neck.getY() - offsetY, neck.getConfidence());
        }

        if( nose.getConfidence() == 0) { nose = new BodyDataPoint(0,0,0); }
        else {
            nose = new BodyDataPoint(nose.getX() - offsetX, nose.getY() - offsetY, nose.getConfidence());
        }
    }

    @Override
    public String toString() {
        return "BodyDataSet{" +
                "nose=" + nose +
                ", neck=" + neck +
                ", shoulder=" + shoulder +
                ", elbow=" + elbow +
                ", wrist=" + wrist +
                ", hip=" + hip +
                ", knee=" + knee +
                ", ankle=" + ankle +
                ", timestamp=" + timestamp +
                '}';
    }


    private double getAngle(BodyDataPoint V, BodyDataPoint A, BodyDataPoint B){ //Find angle AVB
        double result = Math.atan2(B.getY() - V.getY(), B.getX() - V.getX()) -
                        Math.atan2(A.getY() - V.getY(), A.getX() - V.getX());

        return Math.toDegrees(result);
    }

    public double getHipAngle(){
        return getAngle(hip, knee, shoulder);
    }

    public double getKneeAngle(){
        return getAngle(knee, hip, ankle);
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
