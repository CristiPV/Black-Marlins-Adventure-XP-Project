package com.blackmarlins.adventurexp.controller;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.repository.ActivityRepository;
import com.blackmarlins.adventurexp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    // list all activities
    @GetMapping ("/activities")
    public String getListOfActivities(Model model){
        List<Activity> activitiesList = activityService.findAllActivities();
        model.addAttribute("activities", activitiesList);
        return "activitiesList";
    }


    // CREATE -------->

    // display add form
    @GetMapping("/addActivity")
    public String addActivity() {
        return "addActivity";
    }

    // add activity
    @PostMapping("/addActivity")
    public String addActivity(@ModelAttribute Activity activity) {
        System.out.println(activity.toString());
        activityService.saveActivity(activity);
        return "index";
    }


    // READ -------->

    // add view single activity method here...



    // UPDATE -------->

    // display update form
    @GetMapping("/updateActivity/{activityId}")
    public String updateVehicle(@PathVariable("activityId") Long activityId, Model model) {
        model.addAttribute("activity", activityService.findActivityById(activityId));
        return "updateActivity";
    }

    // update activity
    @PostMapping("/updateActivity")
    public String updateActivity(@ModelAttribute Activity activity) {
        activityService.updateActivity(activity);
        return "redirect:/activities";
    }


    // DELETE -------->

    @GetMapping("/deleteActivity/{id}")
    public String deleteActivity(@PathVariable("id") long id) {
        Activity activity = activityService.findActivityById(id);
        activityService.deleteActivity(activity);
        return "index";
    }
}
