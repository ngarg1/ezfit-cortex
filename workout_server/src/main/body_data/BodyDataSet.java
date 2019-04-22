package main.body_data;

import java.util.Date;

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

    public BodyDataPoint getNose() {
        return nose;
    }

    public BodyDataPoint getNeck() {
        return neck;
    }

    public BodyDataPoint getShoulder() {
        return shoulder;
    }

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
}
