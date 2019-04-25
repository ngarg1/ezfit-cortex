package main.classifier;

import main.body_data.BodyDataSet;
import main.body_data.BodyDataSetSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassifierHandlerTest {
    final static String SITUP_BIG_JSON = "{\"version\":1.0,\"people\":[{\"pose_keypoints_2d\":[0,0," +
            "0,326.043,273.881,0.42059,324.768,255.645,0.316504,0,0,0,0,0,0,322.18,289.564,0.419914," +
            "306.472,408.235,0.696461,232.185,412.24,0.687036,515.239,262.172,0.0950522,0,0,0,0,0,0,506.071," +
            "284.346,0.229463,586.954,405.643,0.346295,693.941,384.781,0.435834,0,0,0,0,0,0,0,0,0,264.802,277.812,0.179422]" +
            ",\"face_keypoints\":[],\"hand_left_keypoints\":[],\"hand_right_keypoints\":[]}]}";
    ClassifierHandler classifierHandler;


    @BeforeEach
    void setUp() {
        classifierHandler = new ClassifierHandler();
    }

    @Test
    void classify() {
        BodyDataSet situpBodyData = BodyDataSetSerializer.deserializeJSON(SITUP_BIG_JSON);
        System.out.println(classifierHandler.classify(situpBodyData));
    }
}