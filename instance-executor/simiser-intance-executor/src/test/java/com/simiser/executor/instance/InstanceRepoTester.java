package com.simiser.executor.instance;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.simiser.executor.SimiserIntanceExecutorApplication;
import com.simiser.executor.instance.domain.InstanceRequest;
import com.simiser.executor.instance.domain.RequestType;
import com.simiser.executor.instance.domain.SpotInstance;
import com.simiser.executor.instance.repo.InstanceRequestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimiserIntanceExecutorApplication.class)
public class InstanceRepoTester {
	
	@Autowired
	private InstanceRequestRepository repo;
	
	@Test
	public void get() {
		InstanceRequest ir = new InstanceRequest();
		ir.setUserId("user");
		ir.setData(new SpotInstance());
		ir.setType(RequestType.ADD);
		InstanceRequest saved = repo.save(ir);
		InstanceRequest result = repo.findOne(saved.getId());
		assertThat(result.getId()).isEqualTo(saved.getId());
		
		repo.delete(saved.getId());
		
		assertThat(repo.exists(saved.getId())).isEqualTo(false);
	}
}
