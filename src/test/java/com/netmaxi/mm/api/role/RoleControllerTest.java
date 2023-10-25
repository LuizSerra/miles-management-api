package com.netmaxi.mm.api.role;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netmaxi.mm.api.role.dto.RoleResponseDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class RoleControllerTest {

	@Autowired
	MockMvc mockMvc;

	Gson gson = new Gson();

	@Test
	void ShouldCreateRoleAndReturn200() throws Exception {
		URI uri = new URI("/roles");
		String json = "{\"name\":\"MANAGER\"}";

		var result = this.mockMvc
				.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201)).andReturn();
		
		JSONObject obj = new JSONObject(result.getResponse().getContentAsString());
		Role role = gson.fromJson(obj.toString(), Role.class);
		assertTrue("MANAGER".equals(role.getName()), "The name should be equal the body");
	}

	@Test
	void testShouldReturnAListNonEmpty() throws Exception {
		URI uri = new URI("/roles");
		var result = this.mockMvc
		.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		JSONObject obj = new JSONObject(result.getResponse().getContentAsString());
		JSONArray arr = obj.getJSONArray("content");
		List<RoleResponseDTO> roles = gson.fromJson(arr.toString(), new TypeToken<List<RoleResponseDTO>>(){}.getType());
		assertTrue(roles.size() > 0, "The size should be greater than zero");
	}

}
