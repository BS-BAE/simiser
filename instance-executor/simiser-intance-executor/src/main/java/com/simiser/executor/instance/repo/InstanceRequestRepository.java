package com.simiser.executor.instance.repo;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.simiser.executor.instance.domain.InstanceRequest;

@EnableScan
public interface InstanceRequestRepository extends CrudRepository<InstanceRequest, String> {
}