package com.simiser.executor.instance.domain;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.ec2.model.InstanceType;

import lombok.Data;

@Data
public class SpotInstance {
	private String accessKey;
	private String secretKey;
	private String availableZone;
	private String subnet;
	private Float price;
	private String ami;
	private InstanceType type;
	private String key;
	private List<String> securityGroups;
	private String userData;
	
	public SpotInstance() {
		super();
	}

	public SpotInstance(String accessKey, String secretKey, String availableZone, String subnet, Float price,
			String ami, InstanceType type, String key, List<String> securityGroups, String userData) {
		super();
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.availableZone = availableZone;
		this.subnet = subnet;
		this.price = price;
		this.ami = ami;
		this.type = type;
		this.key = key;
		this.securityGroups = securityGroups;
		this.userData = userData;
	}
	
	public SpotInstance(String accessKey, String secretKey, String availableZone, String subnet, Float price,
			String ami, InstanceType type, String key, String userData, String ... securityGroups) {
		super();
		this.accessKey = accessKey;
		this.secretKey = secretKey;
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