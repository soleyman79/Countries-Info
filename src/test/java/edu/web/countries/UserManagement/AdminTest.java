package edu.web.countries.UserManagement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class AdminTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    public static ResultActions activateUser(String username, String active, String adminUsername, String adminPassword, MockMvc mockMvc) throws Exception {
        ResultActions response = LoginTest.login(adminUsername, adminPassword, mockMvc);
        String responsePayload = response.andReturn().getResponse().getContentAsString();
        Map<String, Object> responseMap = objectMapper.readValue(responsePayload, new TypeReference<>() {
        });
        String token = String.valueOf(responseMap.get("token"));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", username);
        params.add("active", active);
        return mockMvc.
                perform(put("/admin/users")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .params(params)
                        .contentType(MediaType.APPLICATION_JSON)
                );
    }


    @Test
    @Order(1)
    public void loginAdmin(@Value("${admin.username}") String adminUsername, @Value("${admin.password}") String adminPassword) throws Exception {
        ResultActions response = LoginTest.login(adminUsername, adminPassword, this.mockMvc);
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    @Order(2)
    public void getUsers(@Value("${admin.username}") String adminUsername, @Value("${admin.password}") String adminPassword) throws Exception {
        ResultActions response = LoginTest.login(adminUsername, adminPassword, this.mockMvc);
        String responsePayload = response.andReturn().getResponse().getContentAsString();
        Map<String, Object> responseMap = objectMapper.readValue(responsePayload, new TypeReference<>() {
        });
        String token = String.valueOf(responseMap.get("token"));
        this.mockMvc.
                perform(get("/admin/users")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("Users").exists());
    }

    @Test
    @Order(3)
    public void enableUser(@Value("${admin.username}") String adminUsername, @Value("${admin.password}") String adminPassword) throws Exception {
        RegisterTest.register("user", "pass", this.mockMvc);
        activateUser("user", "true", adminUsername, adminPassword, this.mockMvc)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        ;
    }
}
