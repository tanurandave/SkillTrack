package com.skilltrack.model;

public record DashboardData(
    String username,
    String careerRole,
    int streak,
    int xpToday,
    int badges,
    String message
) {}
