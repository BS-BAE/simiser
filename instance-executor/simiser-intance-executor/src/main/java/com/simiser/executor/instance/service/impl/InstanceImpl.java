package com.simiser.executor.instance.service.impl;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.InstanceInterruptionBehavior;
import com.amazonaws.services.ec2.model.LaunchSpecification;
import com.amazonaws.services.ec2.model.RequestSpotInstancesRequest;
import com.amazonaws.services.ec2.model.RequestSpotInstancesResult;
import com.amazonaws.services.ec2.model.SpotInstanceType;
import com.amazonaws.services.ec2.model.SpotPlacement;
import com.simiser.executor.instance.domain.InstanceRequest;
import com.simiser.executor.instance.domain.SpotInstance;
import com.simiser.executor.instance.service.InstanceService;

@Service
public class InstanceImpl implements InstanceService {

	private static final Logger log = Logger.getLogger(InstanceImpl.class);
	
	@Value("${amazon.ec2.userData}")
	private String userData;
	
	@Override
	public InstanceRequest add(InstanceRequest instance) {
		AmazonEC2 ec2 = getClient(instance.getData());
		
		// Initializes a Spot Instance Request
		RequestSpotInstancesRequest request = new RequestSpotInstancesRequest();

		request.setSpotPrice(instance.getData().getPrice().toString());
		request.setInstanceCount(instance.getData().getCount());

		LaunchSpecification launchSpecification = new LaunchSpecification();
		launchSpecification.setImageId(instance.getData().getAmi());
		launchSpecification.setInstanceType(instance.getData().getType());

		String userData = this.userData + instance.getData().getUserData();
		launchSpecification.setSecurityGroups(instance.getData().getSecurityGroups());
		try {
			launchSpecification.setUserData(Base64.encodeBase64String(userData.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
		launchSpecification.setKeyName(instance.getData().getKey());
		launchSpecification.withPlacement(new SpotPlacement(instance.getData().getAvailableZone()));

		request.setLaunchSpecification(launchSpecification);
//		request.setBlockDurationMinutes(hours*60);
		request.setType(SpotInstanceType.OneTime);
		request.setInstanceInterruptionBehavior(InstanceInterruptionBehavior.Terminate);

		// Call the RequestSpotInstance API.
		RequestSpotInstancesResult requestResult = ec2.requestSpotInstances(request);
		instance.getData().setResult(requestResult);
		if(requestResult != null && !requestResult.getSpotInstanceRequests().isEmpty()) {
			instance.setInstanceId(requestResult.getSpotInstanceRequests().get(0).getInstanceId());
		}
		return instance;
	}

	@Override
	public InstanceRequest remove(InstanceRequest instance) {
		return null;
	}
	
	
	AmazonEC2 getClient(SpotInstance instance) {
		return getClient(instance.getAccessKey(), instance.getSecretKey(), instance.getRegion());
	}
	
	private AmazonEC2 getClient(String accessKey, String secretKey, String region) {
		return AmazonEC2ClientBuilder
				.standard()
				.withCredentials(
						new AWSStaticCredentialsProvider(
								new BasicAWSCredentials(accessKey, secretKey)))
				.withRegion(region).build();
	}
	

}
