package edu.web.countries.TokenManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.web.countries.UserManagement.AdminTest;
import edu.web.countries.UserManagement.LoginTest;
import edu.web.countries.UserManagement.RegisterTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class APITokenTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final String username = "mock user";
    private final String password = "mock pass";
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void createToken(@Value("${admin.username}") String adminUser, @Value("${admin.password}") String adminPass) throws Exception {
        RegisterTest.register(this.username, this.password, this.mockMvc);
        AdminTest.activateUser(this.username, "true", adminUser, adminPass, this.mockMvc);
        ResultActions response = LoginTest.login(this.username, this.password, this.mockMvc);
        String token = LoginTest.getTokenByResponse(response);
        String payload = objectMapper.writeValueAsString(Map.of(
                "name", "token-1",
                "expire_date", "2025-01-01T23:59:59Z")
        );
        mockMvc.
                perform(post("/user/api-tokens")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("token-1"))
                .andExpect(jsonPath("$.expire_date").value("2025-01-01T23:59:59Z"))
                .andExpect(jsonPath("$.token").exists())
        ;
    }

    @Test
    @Order(2)
    public void getTokens(@Value("${admin.username}") String adminUser, @Value("${admin.password}") String adminPass) throws Exception {
        RegisterTest.register(this.username, this.password, this.mockMvc);
        AdminTest.activateUser(this.username, "true", adminUser, adminPass, this.mockMvc);
        ResultActions response = LoginTest.login(this.username, this.password, this.mockMvc);
        String token = LoginTest.getTokenByResponse(response);
        mockMvc.
                perform(get("/user/api-tokens")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tokens").exists())
                .andExpect(jsonPath("$.count").exists())
        ;
    }

    @Test
    @Order(3)
    public void deleteToken(@Value("${admin.username}") String adminUser, @Value("${admin.password}") String adminPass) throws Exception {
        RegisterTest.register(this.username, this.password, this.mockMvc);
        AdminTest.activateUser(this.username, "true", adminUser, adminPass, this.mockMvc);
        ResultActions response = LoginTest.login(this.username, this.password, this.mockMvc);
        String token = LoginTest.getTokenByResponse(response);
        mockMvc.
                perform(delete("/user/api-tokens")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Token is Permanent"))
        ;
    }
}
