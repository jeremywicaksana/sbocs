package com.example.springbocs.controller;

import com.example.springbocs.model.entity.Activity;
import com.example.springbocs.model.type.ActivityType;
import com.example.springbocs.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    @Operation(summary="return activities depending on the status",
            description = "Currently there are 2 types of activity CLAIMED and ADDED. That enum will determine" +
                    "which activity will be shown. Unfortunately, atm there isn't really any ADDED enum yet")
        public List<Activity> getActivities(@RequestParam ActivityType status) {
        return activityService.getActivites(status);
    }
}
