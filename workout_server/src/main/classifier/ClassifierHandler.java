package main.classifier;


import com.amazonaws.services.sagemakerruntime.AmazonSageMakerRuntime;
import com.amazonaws.services.sagemakerruntime.AmazonSageMakerRuntimeClientBuilder;
import com.amazonaws.services.sagemakerruntime.model.InvokeEndpointRequest;
import com.amazonaws.services.sagemakerruntime.model.InvokeEndpointResult;
import main.body_data.BodyDataSet;
import main.body_data.BodyDataSetSerializer;
import main.frame_analyzer.Exercise;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ClassifierHandler {
    private static final String CLASSIFY_ENDPOINT = "knn-ml-m4-xlarge-1555626468-775137";
    private static final String CLASSIFIER_CONTENT_TYPE = "application/json";

    AmazonSageMakerRuntime amazonSageMakerRuntimeClient;

    public ClassifierHandler() {
        amazonSageMakerRuntimeClient = AmazonSageMakerRuntimeClientBuilder.standard().build();
    }

    public Exercise classify(BodyDataSet bodyDataSet) {
        InvokeEndpointRequest invokeEndpointRequest = new InvokeEndpointRequest();
        invokeEndpointRequest.setContentType(CLASSIFIER_CONTENT_TYPE);
        try {
            invokeEndpointRequest.setBody(
                    ByteBuffer.wrap(
                            BodyDataSetSerializer.serialize(bodyDataSet).toString().getBytes("UTF-8")
                    )
            );
        } catch (UnsupportedEncodingException uee) {
            System.err.println("Error encoding request to classifier: " + uee.getMessage());
        }

        InvokeEndpointResult classifierResult = amazonSageMakerRuntimeClient.invokeEndpoint(invokeEndpointRequest);

        String body = StandardCharsets.UTF_8.decode(classifierResult.getBody()).toString();
        System.out.println("\n\n--------- Raw Results ----------");
        System.out.println(body);

        return Exercise.valueOf(body);
    }
}