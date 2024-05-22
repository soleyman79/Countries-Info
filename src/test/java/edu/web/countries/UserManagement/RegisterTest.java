package edu.web.countries.UserManagement;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    public void registerSuccessfully() throws Exception {
        String payload = this.objectMapper.writeValueAsString(Map.of("username", "user", "password", "pass"));
        this.mockMvc.
                perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User Created"));
    }

    @Test
    public void registerDuplicatedUsername() throws Exception {
        this.registerSuccessfully();
        String payload = this.objectMapper.writeValueAsString(Map.of("username", "user", "password", "pass"));
        this.mockMvc.
                perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                )
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Username already Exists"));
    }

    @Test
    public void registerAdminUsername() throws Exception {
        String payload = this.objectMapper.writeValueAsString(Map.of("username", this.adminUsername, "password", "pass"));
        this.mockMvc.
                perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                )
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Username already Exists"));
    }

    @Test
    public void registerWithoutUsername() throws Exception {
        assertThrows(ServletException.class, () -> {
            String payload = this.objectMapper.writeValueAsString(Map.of("password", "pass"));
            this.mockMvc.perform(post("/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payload)
            );
        });
    }

    @Test
    public void registerWithoutPassword() throws Exception {
        assertThrows(ServletException.class, () -> {
            String payload = this.objectMapper.writeValueAsString(Map.of("username", "user"));
            this.mockMvc.perform(post("/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payload)
            );
        });
    }
}
