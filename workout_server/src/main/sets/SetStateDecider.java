package sets;

import common.SlidingList;
import frame_analyzer.Exercise;

import java.util.ArrayList;
import java.util.Map;

public class SetStateDecider {
    public static SetState getSetState(Exercise currentExercise, SlidingList<Exercise> exerciseList) {
        if(currentExercise == null) {
            return SetState.INITIAL;
        }
        Map<Exercise, Integer> exerciseCountMap = exerciseList.countList();
        ArrayList<Exercise> maxExercises = new ArrayList<>();
        int maxFrequency = -1;

        for(Map.Entry<Exercise, Integer> mapEntry : exerciseCountMap.entrySet()) {
            if(maxFrequency == -1 || mapEntry.getValue() == maxFrequency) {
                maxExercises.add(mapEntry.getKey());
                maxFrequency = mapEntry.getValue();
            } else if(mapEntry.getValue() > maxFrequency) {
                maxExercises.clear();
                maxExercises.add(mapEntry.getKey());
                maxFrequency = mapEntry.getValue();
            }
        }

        if(maxExercises.size() > 1) {
            if(maxExercises.contains(currentExercise)) {
                System.out.println(exerciseList.getList());
                System.out.println(maxExercises);
                System.out.println(currentExercise);
                return SetState.SAME_SET;
            } else {
                return SetState.UNKNOWN;
            }
        } else {
            if(maxExercises.get(0) == currentExercise) {
                return SetState.SAME_SET;
            } else {
                return SetState.NEW_SET;
            }
        }
    }
}
