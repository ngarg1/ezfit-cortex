package main.body_data;

import org.junit.jupiter.api.Test;

class BodyDataSetSerializerTest {
    final static String SITUP_BIG_JSON = "{\"version\":1.0,\"people\":[{\"pose_keypoints_2d\":[0,0," +
            "0,326.043,273.881,0.42059,324.768,255.645,0.316504,0,0,0,0,0,0,322.18,289.564,0.419914," +
            "306.472,408.235,0.696461,232.185,412.24,0.687036,515.239,262.172,0.0950522,0,0,0,0,0,0,506.071," +
            "284.346,0.229463,586.954,405.643,0.346295,693.941,384.781,0.435834,0,0,0,0,0,0,0,0,0,264.802,277.812,0.179422]" +
            ",\"face_keypoints\":[],\"hand_left_keypoints\":[],\"hand_right_keypoints\":[]}]}";

    final static String FRAME_REQUEST = "POST /workout/frame HTTP/1.1\n" +
            "Host: ec2-54-208-31-8.compute-1.amazonaws.com:8080\n" +
            "Connection: keep-alive\n" +
            "Accept-Encoding: gzip, deflate\n" +
            "Accept: */*\n" +
            "User-Agent: python-requests/2.21.0\n" +
            "Content-Length: 632\n" +
            "Content-Type: multipart/form-data; boundary=c05b4eaa87f3a806cc3a5386c4e9922a\n" +
            "\n" +
            "--c05b4eaa87f3a806cc3a5386c4e9922a\n" +
            "Content-Disposition: form-data; name=\"file\"; filename=\"2019-04-22+16:03:49.472499_keypoints.json\"\n" +
            "\n" +
            "{\"version\":1.2,\"people\":[{\"pose_keypoints_2d\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,318.883," +
            "311.068,0.19351,264.08,311.065,0.18292,0,0,0,0,0,0,371.119,307.14,0.23848,408.919,435.026,0.0647341,0,0,0,0,0," +
            "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"face_keypoints_2d\":[],\"hand_left_keypoints_2d\":[]," +
            "\"hand_right_keypoints_2d\":[],\"pose_keypoints_3d\":[],\"face_keypoints_3d\":[],\"hand_left_keypoints_3d\":[],\"" +
            "hand_right_keypoints_3d\":[]}]}";

    @Test
    void deserializeJSONTest() {
        System.out.println(SITUP_BIG_JSON);
        BodyDataSet situpBodyData = BodyDataSetSerializer.deserializeJSON(SITUP_BIG_JSON);
        System.out.println(situpBodyData);
        System.out.println(BodyDataSetSerializer.serialize(situpBodyData));

        System.out.println(BodyDataSetSerializer.deserializeFrameRequest(FRAME_REQUEST));
    }
}