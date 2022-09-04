package br.com.itau.backendchallenge.controllers;

import br.com.itau.backendchallenge.models.Error;
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

		this.mockMvc.perform(post("/user/password/validate")
				.content("AbTp9!fok")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void whenPasswordIsInvalid_thenReturns400() throws Exception {

		List<Error> errorList = new ArrayList<>();

		Error error = new Error( "password-validation-001", "Campo password deve ser preenchido.");

		errorList.add(error);

		String responseJson = mapper.writeValueAsString(errorList);

		this.mockMvc.perform(post("/user/password/validate")
					.content("")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest())
					.andExpect(content().json(responseJson));

	}

}
