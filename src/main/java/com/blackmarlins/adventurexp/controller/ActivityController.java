package com.blackmarlins.adventurexp.controller;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.model.reservation.Reservation;
import com.blackmarlins.adventurexp.service.ActivityService;
import com.blackmarlins.adventurexp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    private ActivityService activityService;
    private ReservationService reservationService;

    @Autowired
    public ActivityController(ActivityService activityService, ReservationService reservationService) {
        this.activityService = activityService;
        this.reservationService = reservationService;
    }

    // list all activities
    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String getListOfActivities(Model model){
        List<Activity> activitiesList = activityService.findAllActivities();
        model.addAttribute("activities", activitiesList);
        model.addAttribute("isAdmin", LoginController.isAdmin());
        List<Reservation> reservations = activityService.findReservationsByActivity(1);
        return "/activity/activitiesList";
    }


    // CREATE -------->
    // show form for add
    @GetMapping("/addActivity")
    public String addActivity(Model model) {
        model.addAttribute("activity", new Activity());
        return "/activity/activitiesForm";
    }

    // save activity
    @PostMapping("/save")
    public String addActivity(@ModelAttribute("activity") Activity activity) {
        activityService.saveActivity(activity);
        return "redirect:/activities/list";
    }


    // READ -------->
    @GetMapping ("/showFormForRead")
    public String showFormForRead(@RequestParam("activityId") Long id, Model model) {
        // get the employee from the db
        Activity activity = activityService.findActivityById(id);
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("activity", activity);
        // send over to our form
        return "/activity/activitiesRead";
    }


    // UPDATE -------->
    @GetMapping ("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("activityId") Long id, Model model) {
        // get the employee from the db
        Activity activity = activityService.findActivityById(id);
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("activity", activity);
        // send over to our form
        return "/activity/activitiesForm";
    }


    // DELETE -------->
    @GetMapping("/deleteActivity")
    public String delete(@RequestParam("activityId") Long id) {
        // delete the activity
        activityService.deleteActivity(id);
        // redirect to /activities/list
        return "redirect:/activities/list";
    }
}
