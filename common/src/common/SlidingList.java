package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SlidingList<T> {
    private final static int DEFAULT_CAPACITY = 10;

    private int capacity;
    private ArrayList<T> slidingList;

    public SlidingList() {
        this.capacity = DEFAULT_CAPACITY;
        this.slidingList = new ArrayList<>();
    }

    public SlidingList(int capacity) {
        this.capacity = capacity;
        this.slidingList = new ArrayList<>();
    }

    public boolean add(T listObject) {
        if(slidingList.size() == capacity) {
            slidingList.remove(0);
            slidingList.add(listObject);
            return true;
        } else {
            slidingList.add(listObject);
            return false;
        }
    }

    public T getLast(){
        return slidingList.get(slidingList.size()-1);
    }

    public int getSize(){
        return slidingList.size();
    }

    public T get(int index) {
        return slidingList.get(index);
    }

    public ArrayList<T> getList() {
        return slidingList;
    }

    public Map<T, Integer> countList() {
        Map<T, Integer> countMap = new HashMap<>();

        for(T listObject : slidingList) {
            Integer frequency = countMap.get(listObject);
            countMap.put(listObject, (frequency == null) ? 1 : frequency+1);
        }

        return countMap;
    }
}
