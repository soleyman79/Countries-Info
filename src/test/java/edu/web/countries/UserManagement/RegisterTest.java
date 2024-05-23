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
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    public static ResultActions register(String username, String password, MockMvc mockMvc) throws Exception {
        String payload = objectMapper.writeValueAsString(Map.of(
                "username", username,
                "password", password)
        );
        return mockMvc.
                perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                );
    }

    @Test
    @Order(1)
    public void registerSuccessfully() throws Exception {
        register("successful user", "pass", this.mockMvc)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User Created"));
    }

    @Test
    @Order(2)
    public void registerByDuplicatedUsername() throws Exception {
        register("user", "pass", this.mockMvc);
        register("user", "pass", this.mockMvc)
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Username already Exists"))
        ;
    }

    @Test
    @Order(3)
    public void registerByAdminUsername(@Value("${admin.username}") String adminUser, @Value("${admin.password}") String adminPass) throws Exception {
        register(adminUser, adminPass, this.mockMvc)
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Username already Exists"))
        ;
    }

    @Test
    @Order(4)
    public void registerWithoutUsername() {
        assertThrows(Exception.class, () -> {
            String payload = objectMapper.writeValueAsString(Map.of("password", "pass"));
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
            String payload = objectMapper.writeValueAsString(Map.of("username", "user"));
            this.mockMvc.perform(post("/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payload)
            );
        });
    }
}
