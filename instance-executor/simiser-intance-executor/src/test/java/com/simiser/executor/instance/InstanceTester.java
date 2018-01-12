package com.simiser.executor.instance;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.simiser.executor.instance.web.InstanceController;

@RunWith(SpringRunner.class)
@WebMvcTest(InstanceController.class)
public class InstanceTester {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getTest_returnId() throws Exception {
		String id = "test";
		this.mockMvc.perform(get("/instances/{requestId}", id))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(id));
		//containsString(id)
	}
}
