package com.blackmarlins.adventurexp.controller;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

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
}
