package com.skilltrack.controller;

import com.skilltrack.model.DashboardData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @GetMapping("/dashboard")
    public DashboardData getDashboard() {
        return new DashboardData(
            "Nalin Tuscano",
            "Backend Developer",
            3,
            0,
            0,
            "Ready to level up your skills today?"
        );
    }
}
