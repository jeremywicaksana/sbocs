package com.example.springbocs.service;

import com.example.springbocs.model.entity.Activity;
import com.example.springbocs.model.type.ActivityType;
import com.example.springbocs.repository.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityRepo activityRepo;

    /**
     * Return all activies on claimed lost items
     * @return all acitivity object
     */
    public List<Activity> getAllActivities() {
        return activityRepo.findAll();
    }

    /**
     * add the activity record where after an item is claimed or added
     * @param activity the entity of the activity
     */
    public void addActivity(Activity activity) {
        activityRepo.save(activity);
    }

    /**
     * search for list of activites based on the activity enum
     * @param activity
     * @return list of activity
     */
    public List<Activity> getActivites(ActivityType activity){
        return activityRepo.findByActivity(activity);
    }
}
