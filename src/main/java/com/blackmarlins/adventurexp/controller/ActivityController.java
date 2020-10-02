package com.blackmarlins.adventurexp.controller;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping ("/activities")
    public String getListOfActivities(Model model){
        List<Activity> activitiesList = activityRepository.findAll();
        model.addAttribute("activities", activitiesList);
        return "activitiesList";
    }

    @Autowired
    ActivityRepository activityRepository;

    @GetMapping("/addActivity")
    public String addActivity() {
        return "addActivity";
    }

    @PostMapping("/addActivity")
    public String addActivity(@ModelAttribute Activity activity) {
        System.out.println(activity.toString());
        activityRepository.save(activity);

        return "index";
    }

    /*@GetMapping("/updateActivity/{id}")
    public String updateActivity(@PathVariable("id") long id, Model model) {
        model.addAttribute("activity", activityRepository.findById(id));
        return "updateActivity";
    }

    @PostMapping("/updateActivity")
    public String updateActivity(@ModelAttribute Activity activity) {
        System.out.println(activity.toString());
        activityRepository.save(activity);
        return "redirect:/index";
    }*/

    @GetMapping("/deleteActivity/{id}")
    public String deleteActivity(@PathVariable("id") long id) {
        Activity activity = activityRepository.findById(id).get();
        activityRepository.delete(activity);
        return "index";
    }

}
