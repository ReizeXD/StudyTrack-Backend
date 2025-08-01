package com.reize.StudyTrack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reize.StudyTrack.dto.dashboard.DashboardOverviewDTO;
import com.reize.StudyTrack.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/overview")
    public DashboardOverviewDTO getOverview(){
        return dashboardService.getOverview();
    }
}
