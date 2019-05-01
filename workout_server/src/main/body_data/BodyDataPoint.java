package body_data;

public class BodyDataPoint {
    private double x;
    private double y;
    private double confidence;

    public BodyDataPoint(double x, double y, double confidence) {
        this.x = x;
        this.y = y;
        this.confidence = confidence;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getConfidence() {
        return confidence;
    }

    @Override
    public String toString() {
        return "BodyDataPoint{" +
                "x=" + x +
                ", y=" + y +
                ", confidence=" + confidence +
                '}';
    }
}
