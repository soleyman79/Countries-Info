package edu.web.countries.UserManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;


    @Test
    @Order(1)
    public void usernameNotFound() throws Exception {
        String payload = this.objectMapper.writeValueAsString(Map.of("username", "user", "password", "pass"));
        this.mockMvc.
                perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                )
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Username not Found"));
    }

    @Test
    @Order(2)
    public void loginSuccessfully() throws Exception {
        String username = "user";
        String password = "pass";
        String payload = this.objectMapper.writeValueAsString(Map.of("username", username, "password", password));
        this.mockMvc.perform(post("/users/register").contentType(MediaType.APPLICATION_JSON).content(payload));
//        this.register(username, password);
        payload = this.objectMapper.writeValueAsString(Map.of("username", username, "password", password));
        this.mockMvc.
                perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                )
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User is Disabled"));
    }


}
