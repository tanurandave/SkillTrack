package com.skilltrack.controller;

import com.skilltrack.model.RoadmapRequest;
import com.skilltrack.model.RoadmapResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoadmapController {

    @PostMapping("/roadmaps/generate")
    public RoadmapResponse generateRoadmap(@RequestBody RoadmapRequest request) {
        return new RoadmapResponse(
            request.careerRole(),
            request.experienceLevel(),
            List.of(
                "Advanced Java",
                "Spring Boot",
                "REST APIs",
                "Database Design",
                "Microservices Patterns"
            )
        );
    }
}
