package common;

public class FrequencyObject<T> {
    private T keyObject;
    private int frequency;

    public FrequencyObject(T keyObject, int frequency) {
        this.keyObject = keyObject;
        this.frequency = frequency;
    }

    public T getKeyObject() {
        return keyObject;
    }

    public int getFrequency() {
        return frequency;
    }
}
