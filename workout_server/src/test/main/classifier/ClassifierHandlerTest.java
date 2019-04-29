package main.classifier;

import main.body_data.BodyDataSet;
import main.body_data.BodyDataSetSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClassifierHandlerTest {
    final static String SITUP_BIG_JSON = "{\"version\":1.0,\"people\":[{\"pose_keypoints_2d\":[0,0," +
            "0,326.043,273.881,0.42059,324.768,255.645,0.316504,0,0,0,0,0,0,322.18,289.564,0.419914," +
            "306.472,408.235,0.696461,232.185,412.24,0.687036,515.239,262.172,0.0950522,0,0,0,0,0,0,506.071," +
            "284.346,0.229463,586.954,405.643,0.346295,693.941,384.781,0.435834,0,0,0,0,0,0,0,0,0,264.802,277.812,0.179422]" +
            ",\"face_keypoints\":[],\"hand_left_keypoints\":[],\"hand_right_keypoints\":[]}]}";
    final static String SQUAT_BIG_JSON = "{\"version\":1.2,\"people\":[{\"pose_keypoints_2d\":[336.145,289.768,0.913484,440.636,376.012,0.630963,452.947,369.898,0.475723,0,0,0,0,0,0,428.349,379.139,0.807847,259.098,419.177,0.828375,102.109,431.375,0.787794,597.625,640.81,0.574881,606.878,637.671,0.391468,385.313,690.042,0.668617,468.345,902.506,0.736553,594.57,646.966,0.608015,379.078,711.652,0.845096,483.888,936.343,0.757503,0,0,0,354.415,274.383,0.927654,0,0,0,403.674,283.782,0.935193,391.484,963.922,0.725956,412.995,973.279,0.729379,508.422,951.739,0.852057,394.433,914.726,0.768966,403.701,905.554,0.676426,489.98,917.707,0.175136],\"face_keypoints_2d\":[],\"hand_left_keypoints_2d\":[],\"hand_right_keypoints_2d\":[],\"pose_keypoints_3d\":[],\"face_keypoints_3d\":[],\"hand_left_keypoints_3d\":[],\"hand_right_keypoints_3d\":[]}]}";
    final static String PLANK_BIG_JSON = "{\"version\":1.0,\"people\":[{\"pose_keypoints_2d\":[0,0,0,326.043,273.881,0.42059,324.768,255.645,0.316504,0,0,0,0,0,0,322.18,289.564,0.419914,306.472,408.235,0.696461,232.185,412.24,0.687036,515.239,262.172,0.0950522,0,0,0,0,0,0,506.071,284.346,0.229463,586.954,405.643,0.346295,693.941,384.781,0.435834,0,0,0,0,0,0,0,0,0,264.802,277.812,0.179422],\"face_keypoints\":[],\"hand_left_keypoints\":[],\"hand_right_keypoints\":[]}]}";

    ClassifierHandler classifierHandler;


    @BeforeEach
    void setUp() {
        classifierHandler = new ClassifierHandler();
    }

    @Test
    void classify() {
        //BodyDataSet situpBodyData = BodyDataSetSerializer.deserializeJSON(SITUP_BIG_JSON);
        //System.out.println(classifierHandler.classify(situpBodyData));
        //BodyDataSet squatBodyData = BodyDataSetSerializer.deserializeJSON(SQUAT_BIG_JSON);
        //System.out.println(classifierHandler.classify(squatBodyData));
        BodyDataSet plankBodyData = BodyDataSetSerializer.deserializeJSON(PLANK_BIG_JSON);
        System.out.println(classifierHandler.classify(plankBodyData));
    }
}