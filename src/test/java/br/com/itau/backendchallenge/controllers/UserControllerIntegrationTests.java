package br.com.itau.backendchallenge.controllers;

import br.com.itau.backendchallenge.models.ValidatePasswordRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class UserControllerIntegrationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void mustValidatePasswordWithSuccess() throws Exception {

		ValidatePasswordRequest request = new ValidatePasswordRequest();

		request.setPassword("AbTp9!fok");

		String requestJson = mapper.writeValueAsString(request);

		mockMvc.perform(post("/user/password/validate")
				.content(requestJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

}
