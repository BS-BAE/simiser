package com.simiser.executor.instance.service;

import com.simiser.executor.instance.domain.InstanceRequest;

public interface InstanceService {

	InstanceRequest add(InstanceRequest instance);
	
	InstanceRequest remove(InstanceRequest instance);
	
}
