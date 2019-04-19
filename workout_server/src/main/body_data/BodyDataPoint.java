package main.body_data;

public class BodyDataPoint {
    private int x;
    private int y;
    private int confidence;

    public BodyDataPoint(int x, int y, int confidence) {
        this.x = x;
        this.y = y;
        this.confidence = confidence;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getConfidence() {
        return confidence;
    }
}
