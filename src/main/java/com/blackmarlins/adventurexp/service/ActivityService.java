package com.blackmarlins.adventurexp.service;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }

    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }

    public Activity findActivityById(Long activityId) {
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);
        return optionalActivity.orElseGet(() -> new Activity("No result", "No result", 0.0, 0));
    }

    public void updateActivity(Activity activity) {
        activityRepository.save(activity);
    }

    public void deleteActivity(Activity activity) {
        activityRepository.delete(activity);
    }
}
