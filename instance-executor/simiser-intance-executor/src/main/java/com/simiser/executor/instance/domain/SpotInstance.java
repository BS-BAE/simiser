package com.simiser.executor.instance.domain;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.RequestSpotInstancesResult;

import lombok.Data;

@Data
public class SpotInstance {
	private String accessKey;
	private String secretKey;
	private String region;
	private String availableZone;
	private String subnet;
	private Float price;
	private Integer count  = 1;
	private String ami;
	private InstanceType type;
	private String key;
	private List<String> securityGroups;
	private String userData;
	
	private RequestSpotInstancesResult result;
	
	public SpotInstance() {
		super();
	}

	public SpotInstance(String accessKey, String secretKey, String region, String availableZone, String subnet, Float price,
			String ami, InstanceType type, String key, String userData, List<String> securityGroups) {
		super();
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.region = region;
		this.availableZone = availableZone;
		this.subnet = subnet;
		this.price = price;
		this.ami = ami;
		this.type = type;
		this.key = key;
		this.securityGroups = securityGroups;
		this.userData = userData;
	}
	
	public SpotInstance(String accessKey, String secretKey, String region, String availableZone, String subnet, Float price,
			String ami, InstanceType type, String key, String userData, String ... securityGroups) {
		super();
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.region = region;
		this.availableZone = availableZone;
		this.subnet = subnet;
		this.price = price;
		this.ami = ami;
		this.type = type;
		this.key = key;
		this.securityGroups = Arrays.asList(securityGroups);
		this.userData = userData;
	}
	
}