package com.miniproject.employee_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniproject.employee_service.model.Employee;

import jakarta.annotation.PostConstruct;

@Service
public class PublishService {

	@Autowired
	private ObjectMapper mapper;

	private AmazonSNS snsClient;

	@Value("${aws.sns.topicArn}")
	private String topicArn;

	@Value("${aws.accessKey}")
	private String awsAccessKey;

	@Value("${aws.secretKey}")
	private String awsSecretKey;

	@Value("${aws.region}")
	private String awsRegion;

	@PostConstruct
	public void initializeAWSClient() {
		AWSCredentialsProvider credentials = new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(awsAccessKey, awsSecretKey));
		this.snsClient = AmazonSNSClientBuilder.standard().withRegion(awsRegion).withCredentials(credentials).build();
	
	}

	public void publishToSns(Employee employee) {
		String message;
		try {
			message = mapper.writeValueAsString(employee);
			PublishRequest publishRequest = new PublishRequest(topicArn, message);
			PublishResult publishResult = snsClient.publish(publishRequest);
			System.out.println("MessageId: " + publishResult.getMessageId());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
