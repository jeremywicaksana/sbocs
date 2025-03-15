package com.example.springbocs.controller;

import com.example.springbocs.model.entity.Activity;
import com.example.springbocs.model.type.ActivityType;
import com.example.springbocs.service.ActivityService;
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
        public List<Activity> getActivities(@RequestParam ActivityType status) {
        return activityService.getActivites(status);
    }
}
