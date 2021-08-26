package br.com.itau.backendchallenge.controllers;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;
import br.com.itau.backendchallenge.models.ApiErrorResponse;
import br.com.itau.backendchallenge.models.Error;
import br.com.itau.backendchallenge.models.ValidatePasswordRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { ExceptionController.class, UserController.class })
public class ExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void mustValidatePasswordWithError() throws Exception {

        ValidatePasswordRequest request = new ValidatePasswordRequest();

        request.setPassword(null);

        String requestJson = mapper.writeValueAsString(request);

        List<Error> errorList = new ArrayList<>();

        Error error = new Error(400, "password-validation-001", "Campo password não preenchido.");

        errorList.add(error);

        InvalidPasswordException exception = new InvalidPasswordException(errorList);

        ApiErrorResponse response = new ApiErrorResponse(errorList);

        String responseJson = mapper.writeValueAsString(response);

        doThrow(exception).when(userController).validatePassword(any(ValidatePasswordRequest.class));

        mockMvc.perform(post("/user/password/validate")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(responseJson));

    }

}
