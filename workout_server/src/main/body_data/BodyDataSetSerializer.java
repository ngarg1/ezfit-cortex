package main.body_data;

import main.body_data.BodyDataSet;
import org.json.simple.*;


public class BodyDataSetSerializer {
    public static JSONObject serialize(BodyDataSet bodyDataSet) {
        JSONObject obj = new JSONObject();
	int feature_list[24];

	feature_list[0] = bodyDataSet.getNose().getX();
	feature_list[1] = bodyDataSet.getNose().getY();
	feature_list[2] = bodyDataSet.getNose().getConfidence()

	feature_list[3] = bodyDataSet.getNeck().getX();
	feature_list[4] = bodyDataSet.getNeck().getY();
	feature_list[5] = bodyDataSet.getNeck().getConfidence();

	feature_list[6] = bodyDataSet.getShoulder().getX();
	feature_list[7] = bodyDataSet.getShoulder().getY();
	feature_list[8] = bodyDataSet.getShoulder().getConfidence();
	  
	feature_list[9] = bodyDataSet.getElbow().getX();
	feature_list[10] = bodyDataSet.getElbow().getY();
	feature_list[11] = bodyDataSet.getElbow().getConfidence();

	feature_list[12] = bodyDataSet.getWrist().getX();
	feature_list[13] = bodyDataSet.getWrist().getY();
	feature_list[14] = bodyDataSet.getWrist().getConfidence();

	feature_list[15] = bodyDataSet.getHip().getX();
	feature_list[16] = bodyDataSet.getHip().getY();
	feature_list[17] = bodyDataSet.getHip().getConfidence();

	feature_list[18] = bodyDataSet.getKnee().getX();
	feature_list[19] = bodyDataSet.getKnee().getY();
	feature_list[20] = bodyDataSet.getKnee().getConfidence();

	feature_list[21] = bodyDataSet.getAnkle().getX();
	feature_list[22] = bodyDataSet.getAnkle().getY();
	feature_list[23] = bodyDataSet.getAnkle().getConfidence();

	obj.put("features", feature_list);
	
        return obj;
    }

    public static BodyDataSet deserializeJSON(String bodyDataJSON) {
        JSONObject obj = new JSONObject(bodyDataJson);
	JSONArray data = obj.getJSONArray("people")[1].getJSONArray("pose_keypoints");
	
	BodyDataPoint nose = new BodyDataPoint(data[0], data[1], data[2]);
	BodyDataPoint neck = new BodyDataPoint(data[3], data[4], data[5]);
	BodyDataPoint shoulder = new BodyDataPoint(data[6], data[7], data[8]);
	BodyDataPoint elbow = new BodyDataPoint(data[9], data[10], data[1]);
	BodyDataPoint wrist = new BodyDataPoint(data[12], data[13], data[14]);
	BodyDataPoint hip = new BodyDataPoint(data[15], data[16], data[17]);
	BodyDataPoint knee = new BodyDataPoint(data[18], data[19], data[20]);
	BodyDataPoint ankle = new BodyDataPoint(data[21], data[22], data[23]);
	
	BodyDataSet bds = new BodyDataSet(nose,neck,shoulder,elbow,wrist,hip,knee,ankle);

        return bds;
    }

    public static BodyDataSet deserializeFrameRequest(String frameRequest) {
	String[] parse_header = frameRequest.split("\r\n");
	int i = 0;
	while ( !parse_header[i].equals("") ){ //loop until empty string
	    i += 1;
	}
	deserializeJSON( parse_header[i] ) //parse JSON body
        return null;
    }
}
