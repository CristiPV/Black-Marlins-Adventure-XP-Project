package com.blackmarlins.adventurexp.service;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }

    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }

    public Activity findActivityById(Long activityId) {
        Optional<Activity> result = activityRepository.findById(activityId);
        Activity activity = null;
        if (result.isPresent()) {
            activity = result.get();
        }
        return activity;
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
