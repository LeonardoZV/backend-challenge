package br.com.itau.backendchallenge.controllers;

import br.com.itau.backendchallenge.models.ValidatePasswordRequest;
import br.com.itau.backendchallenge.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void mustValidatePasswordWithSuccess() throws Exception {

        ValidatePasswordRequest request = new ValidatePasswordRequest();

        request.setPassword("AbTp9!fok");

        String requestJson = mapper.writeValueAsString(request);

        when(userService.isValidPassword(any(String.class))).thenReturn(true);

        mockMvc.perform(post("/user/password/validate")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
