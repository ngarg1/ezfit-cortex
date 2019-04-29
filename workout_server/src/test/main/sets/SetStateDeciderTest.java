package main.sets;

import common.SlidingList;
import main.frame_analyzer.Exercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetStateDeciderTest {
    SlidingList<Exercise> exerciseList1;
    SlidingList<Exercise> exerciseList2;

    @BeforeEach
    void setUp() {
        exerciseList1 = new SlidingList<>();
        exerciseList2 = new SlidingList<>();

        exerciseList1.add(Exercise.SQUAT);
        exerciseList1.add(Exercise.SQUAT);
        exerciseList1.add(Exercise.SQUAT);
        exerciseList1.add(Exercise.SITUP);
        exerciseList1.add(Exercise.SITUP);

        exerciseList2.add(Exercise.PLANK);
        exerciseList2.add(Exercise.SQUAT);
        exerciseList2.add(Exercise.SQUAT);
        exerciseList2.add(Exercise.SITUP);
        exerciseList2.add(Exercise.SITUP);
    }

    @Test
    void getSetState() {
        assertEquals(SetStateDecider.getSetState(Exercise.SQUAT, exerciseList1), SetState.SAME_SET);
        assertEquals(SetStateDecider.getSetState(Exercise.SITUP, exerciseList1), SetState.NEW_SET);
        assertEquals(SetStateDecider.getSetState(Exercise.SQUAT, exerciseList2), SetState.SAME_SET);
        assertEquals(SetStateDecider.getSetState(Exercise.SITUP, exerciseList2), SetState.SAME_SET);
        assertEquals(SetStateDecider.getSetState(Exercise.PLANK, exerciseList2), SetState.UNKNOWN);

    }
}