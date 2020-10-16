package com.blackmarlins.adventurexp.controller;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    // Get All Activities
    @GetMapping("/list")
    public String findAll(Model model){
        model.addAttribute("activities", activityService.findAllActivities());
        model.addAttribute("isAdmin", LoginController.isAdmin());
        return "activity/activity";
    }

    // Find one by id
    @RequestMapping("/findById")
    @ResponseBody
    public Activity findById(Long id)
    {
        Activity activity = activityService.findActivityById(id);
        /* clears the list of the reservations attribute in order to
        avoid stack overflow error, as it was entering into a recursive circle
        from reservations to activity... */
        activity.getReservations().clear();
        return activity;
    }

    // Add Activity
    @PostMapping("/addNew")
    public String addNew(Activity activity) {
        activityService.saveActivity(activity);
        return "redirect:/activities/list";
    }

    // Update
    @RequestMapping(value="/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Activity activity) {
        activityService.saveActivity(activity);
        return "redirect:/activities/list";
    }

    // Delete
    @RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Long id) {
        activityService.deleteActivity(id);
        return "redirect:/activities/list";
    }

}
