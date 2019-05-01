package body_data;

import common.BufferHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;


public class BodyDataSetSerializer {
	private final static String PEOPLE_KEY = "people";
	private final static String BODY_DATA_POINTS_KEY = "pose_keypoints_2d";

	public static JSONObject serialize(BodyDataSet bodyDataSet) {
        JSONObject obj = new JSONObject();
		double[] feature_list = new double[24];

		feature_list[0] = bodyDataSet.getNose().getX();
		feature_list[1] = bodyDataSet.getNose().getY();
		feature_list[2] = bodyDataSet.getNose().getConfidence();

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
		try {
			JSONObject obj = new JSONObject(bodyDataJSON);
			JSONArray data = (obj.getJSONArray(PEOPLE_KEY).getJSONObject(0).getJSONArray(BODY_DATA_POINTS_KEY));

			BodyDataPoint nose = new BodyDataPoint(data.getDouble(0), data.getDouble(1), data.getDouble(2));
			BodyDataPoint neck = new BodyDataPoint(data.getDouble(3), data.getDouble(4), data.getDouble(5));
			BodyDataPoint shoulder = new BodyDataPoint(data.getDouble(15), data.getDouble(16), data.getDouble(17));
			BodyDataPoint elbow = new BodyDataPoint(data.getDouble(18), data.getDouble(19), data.getDouble(20));
			BodyDataPoint wrist = new BodyDataPoint(data.getDouble(21), data.getDouble(22), data.getDouble(23));
			BodyDataPoint hip = new BodyDataPoint(data.getDouble(36), data.getDouble(37), data.getDouble(38));
			BodyDataPoint knee = new BodyDataPoint(data.getDouble(39), data.getDouble(40), data.getDouble(41));
			BodyDataPoint ankle = new BodyDataPoint(data.getDouble(42), data.getDouble(43), data.getDouble(44));

			BodyDataSet bds = new BodyDataSet(nose, neck, shoulder, elbow, wrist, hip, knee, ankle, new Date());
			bds.normalize();
			return bds;
		} catch (JSONException je) {
			System.out.println("No People in frame!");
			return null;
		}
    }

    public static BodyDataSet deserializeFrameRequest(String frameRequest) {
		BufferedReader httpStringReader = new BufferedReader(new StringReader(frameRequest));
		String line = "";
		int newLineCount = 0;
		while(newLineCount < 1) {
			try {
				line = httpStringReader.readLine();
			} catch (IOException ioe) {
				System.err.println("Error parsing Frame Request " + ioe.getMessage());
			}
			if(line.equals("")) {
				newLineCount++;
			}
		}
		return deserializeJSON(BufferHandler.readAllLines(httpStringReader));
    }
}
