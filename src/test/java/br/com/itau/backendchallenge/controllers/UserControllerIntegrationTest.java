package br.com.itau.backendchallenge.controllers;

import br.com.itau.backendchallenge.models.ApiErrorResponse;
import br.com.itau.backendchallenge.models.Error;
import br.com.itau.backendchallenge.models.ValidatePasswordRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

	private final MockMvc mockMvc;

	private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public UserControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void whenPasswordIsValid_thenReturns200() throws Exception {

		ValidatePasswordRequest request = new ValidatePasswordRequest();

		request.setPassword("AbTp9!fok");

		String requestJson = mapper.writeValueAsString(request);

		this.mockMvc.perform(post("/user/password/validate")
				.content(requestJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void whenPasswordIsInvalid_thenReturns400() throws Exception {

		ValidatePasswordRequest request = new ValidatePasswordRequest();

		request.setPassword(null);

		String requestJson = mapper.writeValueAsString(request);

		List<Error> errorList = new ArrayList<>();

		Error error = new Error( "password-validation-001", "Campo password deve ser preenchido.");

		errorList.add(error);

		ApiErrorResponse response = new ApiErrorResponse(errorList);

		String responseJson = mapper.writeValueAsString(response);

		this.mockMvc.perform(post("/user/password/validate")
						.content(requestJson)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(responseJson));

	}

}
