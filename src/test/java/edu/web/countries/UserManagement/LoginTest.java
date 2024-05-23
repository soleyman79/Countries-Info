package edu.web.countries.UserManagement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    public static ResultActions login(String username, String password, MockMvc mockMvc) throws Exception {
        String payload = objectMapper.writeValueAsString(Map.of(
                "username", username,
                "password", password)
        );
        return mockMvc.
                perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                );
    }

    public static String getTokenByResponse(ResultActions response) throws Exception {
        String responsePayload = response.andReturn().getResponse().getContentAsString();
        Map<String, Object> responseMap = objectMapper.readValue(responsePayload, new TypeReference<>() {
        });
        return String.valueOf(responseMap.get("token"));
    }

    @Test
    @Order(1)
    public void loginSuccessfully(@Value("${admin.username}") String adminUser, @Value("${admin.password}") String adminPass) throws Exception {
        RegisterTest.register("user", "pass", this.mockMvc);
        AdminTest.activateUser("user", "true", adminUser, adminPass, this.mockMvc);
        login("user", "pass", this.mockMvc)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").exists())
        ;
    }

    @Test
    @Order(2)
    public void loginByUsernameNotFound() throws Exception {
        login("imaginary user", "pass", this.mockMvc)
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Username not Found"))
        ;
    }

    @Test
    @Order(3)
    public void loginByDisabledUsername() throws Exception {
        RegisterTest.register("disable user", "pass", this.mockMvc);
        login("disable user", "wrong pass", this.mockMvc)
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User is Disabled"))
        ;
    }

    @Test
    @Order(4)
    public void loginByWrongPassword(@Value("${admin.username}") String adminUser, @Value("${admin.password}") String adminPass) throws Exception {
        RegisterTest.register("user", "pass", this.mockMvc);
        AdminTest.activateUser("user", "true", adminUser, adminPass, this.mockMvc);
        login("user", "wrong pass", this.mockMvc)
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        ;
    }
}
