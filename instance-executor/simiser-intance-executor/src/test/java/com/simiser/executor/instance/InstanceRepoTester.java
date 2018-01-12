package com.simiser.executor.instance;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.simiser.executor.SimiserIntanceExecutorApplication;
import com.simiser.executor.instance.domain.InstanceRequest;
import com.simiser.executor.instance.repo.InstanceRequestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimiserIntanceExecutorApplication.class)
public class InstanceRepoTester {
	
	@Autowired
	private InstanceRequestRepository repo;
	
	@Test
	public void get() {
		InstanceRequest ir = repo.findOne("test");
		assertThat(ir.getRequestId()).isEqualTo("test");
	}
}
