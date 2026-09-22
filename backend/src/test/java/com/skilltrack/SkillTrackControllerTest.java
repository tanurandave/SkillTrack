package com.skilltrack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SkillTrackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnDashboardData() throws Exception {
        mockMvc.perform(get("/api/dashboard"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("Nalin Tuscano"))
            .andExpect(jsonPath("$.careerRole").value("Backend Developer"));
    }

    @Test
    void shouldCreateSkill() throws Exception {
        mockMvc.perform(post("/api/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Docker\",\"proficiency\":4}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Docker"));
    }

    @Test
    void shouldGenerateRoadmap() throws Exception {
        mockMvc.perform(post("/api/roadmaps/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"careerRole\":\"Backend Developer\",\"experienceLevel\":\"Advanced\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.careerRole").value("Backend Developer"));
    }
}
