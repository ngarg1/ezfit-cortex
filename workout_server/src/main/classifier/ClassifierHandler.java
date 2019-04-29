package main.classifier;

import main.body_data.BodyDataSet;
import main.body_data.BodyDataSetSerializer;
import main.frame_analyzer.Exercise;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class ClassifierHandler {
    private static final String CLASSIFIER_URL_STRING = "https://xyq1ojbctg.execute-api.us-east-1.amazonaws.com/api";
    private static final String RESPONSE_EXERCISE_KEY = "label";

    HttpClient classifierClient;

    public ClassifierHandler() {
        classifierClient = HttpClients.createDefault();
    }

    public Exercise classify(BodyDataSet bodyDataSet) {
        HttpPost classifyPostRequest = new HttpPost(CLASSIFIER_URL_STRING);
        HttpEntity body = null;
        try {
            body = new ByteArrayEntity(BodyDataSetSerializer.serialize(bodyDataSet).toString().getBytes("UTF-8"));
            System.out.println(BodyDataSetSerializer.serialize(bodyDataSet).toString());
        } catch (Exception e) {
            System.err.println("Error serializing Body Data " + e.getMessage());
        }
        classifyPostRequest.setEntity(body);

        HttpResponse response = null;
        try {
            response = classifierClient.execute(classifyPostRequest);
        } catch (Exception e) {
            System.err.println("Error sending classifier request " + e.getMessage());
        }
        System.out.println("\n\n--------- Raw Results ----------");
        String responseString = "";
        try {
            responseString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            System.err.println("Error understanding classifier response " + e.getMessage());
        }
        JSONObject responseJSON = new JSONObject(responseString);
        Exercise exercise = Exercise.valueOf(responseJSON.getString(RESPONSE_EXERCISE_KEY).toUpperCase());

        return exercise;
    }
}