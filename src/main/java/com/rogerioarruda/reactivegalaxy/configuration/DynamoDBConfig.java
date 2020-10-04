package com.rogerioarruda.reactivegalaxy.configuration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

@Configuration
public class DynamoDBConfig {

	private final String dynamoDbEndPointUrl;

	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;

	public DynamoDBConfig(@Value("${amazon.dynamodb.endpoint}") String dynamoDbEndPointUrl) {
		this.dynamoDbEndPointUrl = dynamoDbEndPointUrl;
	}

	@Bean
	public DynamoDbAsyncClient getDynamoDbAsyncClient() {
		return DynamoDbAsyncClient.builder().credentialsProvider(amazonAWSCredentials())
				.endpointOverride(URI.create(dynamoDbEndPointUrl)).build();
	}

	@Bean
	public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient() {
		return DynamoDbEnhancedAsyncClient.builder().dynamoDbClient(getDynamoDbAsyncClient()).build();
	}

	@Bean
	public AwsCredentialsProvider amazonAWSCredentials() {
		return new AwsCredentialsProvider() {

			@Override
			public AwsCredentials resolveCredentials() {
				return new AwsCredentials() {

					@Override
					public String secretAccessKey() {
						return amazonAWSAccessKey;
					}

					@Override
					public String accessKeyId() {
						return amazonAWSSecretKey;
					}
				};
			}
		};
	}

}
