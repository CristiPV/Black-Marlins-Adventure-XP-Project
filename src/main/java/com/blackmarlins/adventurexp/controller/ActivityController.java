package com.blackmarlins.adventurexp.controller;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping ("/activities")
    public String getListOfActivities(Model model){
        List<Activity> activitiesList = activityService.findAllActivities();
        model.addAttribute("activities", activitiesList);
        return "activitiesList";
    }

    //display update form
    @GetMapping("/updateActivity/{activityId}")
    public String updateVehicle(@PathVariable("activityId") Long activityId, Model model) {
        model.addAttribute("activity", activityService.findActivityById(activityId));
        return "updateVehicle";
    }

    //update activity information
    @PostMapping("/updateActivity")
    public String updateActivity(@ModelAttribute Activity activity) {
        activityService.updateActivity(activity);
        return "redirect:/activities";
    }
}
