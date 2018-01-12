package com.simiser.executor.instance.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instances")
public class InstanceController {

	@GetMapping("/{requestId}")
	public String get(@PathVariable String requestId) {
		return requestId;
	}
}
