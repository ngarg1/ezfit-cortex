package main.frontend_handler;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import main.analyses.FrameAnalysis;
import main.frame_analyzer.Exercise;

public class FrontendHandler {
    private final String CLIENT_REGION = "us-east-1";
    private final String BUCKET_NAME = "openpose-images";
    private final String STRING_OBJECT_KEY_NAME = "workout_analysis";

    private AmazonS3 s3Client;

    public FrontendHandler() {
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(CLIENT_REGION)
                .withCredentials(new ProfileCredentialsProvider())
                .build();
    }

    public void sendDisplayRequest(Exercise exercise) {
        s3Client.putObject(BUCKET_NAME, STRING_OBJECT_KEY_NAME, exercise.toString());
    }
}
