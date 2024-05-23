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
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class RegisterTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;

    private ResultActions register(String username, String password) throws Exception {
        String payload = this.objectMapper.writeValueAsString(Map.of(
                "username", username,
                "password", password)
        );
        return this.mockMvc.
                perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                );
    }

    @Test
    @Order(1)
    public void registerSuccessfully() throws Exception {
        this.register("user", "pass")
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User Created"));
    }

    @Test
    @Order(2)
    public void registerDuplicatedUsername() throws Exception {
        register("user", "pass");
        register("user", "pass")
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Username already Exists"));
    }

    @Test
    @Order(3)
    public void registerAdminUsername() throws Exception {
        this.register(this.adminUsername, this.adminPassword)
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Username already Exists"));
    }

    @Test
    @Order(4)
    public void registerWithoutUsername() {
        assertThrows(Exception.class, () -> {
            String payload = this.objectMapper.writeValueAsString(Map.of("password", "pass"));
            this.mockMvc.perform(post("/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payload)
            );
        });
    }

    @Test
    @Order(5)
    public void registerWithoutPassword() {
        assertThrows(Exception.class, () -> {
            String payload = this.objectMapper.writeValueAsString(Map.of("username", "user"));
            this.mockMvc.perform(post("/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payload)
            );
        });
    }
}
