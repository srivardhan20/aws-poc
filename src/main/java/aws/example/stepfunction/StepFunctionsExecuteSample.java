package aws.example.stepfunction;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;

/**
 * This sample demonstrates how to make basic requests to Amazon Step Functions
 * using the AWS SDK for Java.
 * <p>
 * <b>Prerequisites:</b> You must have a valid AWS developer account.
 * <p>
 * Fill in your AWS access credentials in the provided credentials file
 * template, and be sure to move the file to the default location
 * (~/.aws/credentials) where the sample code will load the credentials from.
 * <p>
 * <b>WARNING:</b> To avoid accidental leakage of your credentials, DO NOT keep
 * the credentials file in your source directory.
 */
public class StepFunctionsExecuteSample {

	public static void main(String[] args) throws Exception {
		/*
		 * The ProfileCredentialsProvider will return your [default] credential profile
		 * by reading from the credentials file located at (~/.aws/credentials).
		 *
		 * It is possible to use another profile with: credentialsProvider = new
		 * ProfileCredentialsProvider("your-profile")
		 */

		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		try {
			credentialsProvider.getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles "
					+ "file. Please make sure that your credentials file is "
					+ "at the correct location (~/.aws/credentials), and is " + "in valid format.", e);
		}

		// TODO replace with your step function specific region
		Regions region = Regions.AP_SOUTH_1;
		AWSStepFunctions sfnClient = AWSStepFunctionsClientBuilder.standard().withCredentials(credentialsProvider)
				.withRegion(region).build();

		System.out.println("===========================================");
		System.out.println("Getting Started with Amazon Step Functions");
		System.out.println("===========================================\n");

		try {
			// TODO replace with your step function arn
			String arn = "arn:aws:states:ap-south-1:430066668348:stateMachine:HelloWorld";
			// TODO replace with your step function request
			String stepFunctionRequest = "{ \"IsHelloWorldExample\": true}";
			StartExecutionRequest request = new StartExecutionRequest().withStateMachineArn(arn)
					.withInput(stepFunctionRequest);
			StartExecutionResult result = sfnClient.startExecution(request);
			System.out.println("Execution Arn: " + result.getExecutionArn());
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means"
					+ " your request made it to Amazon Step Functions, but was"
					+ " rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means "
					+ "the client encountered a serious internal problem while "
					+ "trying to communicate with Step Functions, such as not " + "being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}
}
