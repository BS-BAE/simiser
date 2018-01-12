package com.simiser.executor.instance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.ec2.model.InstanceType;
import com.simiser.executor.SimiserIntanceExecutorApplication;
import com.simiser.executor.instance.domain.InstanceRequest;
import com.simiser.executor.instance.domain.RequestType;
import com.simiser.executor.instance.domain.SpotInstance;
import com.simiser.executor.instance.service.InstanceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimiserIntanceExecutorApplication.class)
public class InstanceServiceTester {
	
	@Autowired
	private InstanceService service;
	
	@Test
	public void addInstance() {
		InstanceRequest ir = new InstanceRequest("bbs"
				, RequestType.ADD
				, new SpotInstance(""
						, ""
						, "eu-west-1"
						, "eu-west-1a"
						, "subnet-f9b9b89e"
						, 0.0022f
						, "ami-8961fbfe"
						, InstanceType.T1Micro
						, "testkey"
						, "sudo curl www.naver.com >> naver.txt\\nyum update -y"
						, "spot-sg")
		);
		service.add(ir);
	}
}
