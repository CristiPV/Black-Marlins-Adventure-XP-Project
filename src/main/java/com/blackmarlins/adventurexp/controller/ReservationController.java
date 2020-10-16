package com.blackmarlins.adventurexp.controller;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.model.reservation.Reservation;
import com.blackmarlins.adventurexp.model.reservation.ReservationFlow;
import com.blackmarlins.adventurexp.service.ActivityService;
import com.blackmarlins.adventurexp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@SessionAttributes("reservationFlow")
public class ReservationController {

    private ActivityService activityService;
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ActivityService activityService, ReservationService reservationService) {
        this.activityService = activityService;
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation/list")
    public String findAll(Model model, @RequestParam(value="activityName", required = false) String activityName) {
        model.addAttribute("activities", activityService.findAllActivities());
        model.addAttribute("isAdmin", LoginController.isAdmin());
        if (activityName != null) {
            if (activityName.equals("all")) {
                model.addAttribute("reservations", reservationService.findAllActiveReservations());
            } else {
                model.addAttribute("reservations", reservationService.findByActivityName(activityName));
            }
        } else {
            model.addAttribute("reservations", reservationService.findAllActiveReservations());
        }
        System.out.println(activityName);
        return "reservation/reservationList";
    }

    /**
     * Since {@code reservationFlow} is used in the {@code SessionAttributes} on the controller level, it informs
     * spring to treat our {@code ReservationFlow} as session scoped. This method will be invoked the very first
     * HTTP request to populate the session with the new object.
     * <p>Subsequent HTTP requests will go directly to the handler and the statement
     * {@code @ModelAttribute("reservationFlow")} will grab the object directly from the session rather
     * than recreating it.
     */
    @ModelAttribute("reservationFlow")
    public ReservationFlow getReservationFlow() {
        return new ReservationFlow();
    }



    // Flow step 1 - add date, duration (in hours), and number of people

    @GetMapping("/reservation")
    public String getDateForm(@RequestParam(value = "activityId") Long activityId,
                              @ModelAttribute("reservationFlow") ReservationFlow reservationFlow) {
        reservationFlow.enterStep(ReservationFlow.Step.Dates);
        Activity activity = activityService.findActivityById(activityId);
        reservationFlow.getReservation().setActivity(activity);
        return "reservation/dates";
    }

    @PostMapping("/reservation/dates")
    public String dates(@ModelAttribute("reservationFlow") ReservationFlow reservationFlow, RedirectAttributes redirectAttributes) {
        reservationFlow.enterStep(ReservationFlow.Step.Dates);
        reservationFlow.completeStep(ReservationFlow.Step.Dates);
        redirectAttributes.addFlashAttribute("reservationFlow", reservationFlow);
        return "redirect:/reservation/customer";
    }

    @PostMapping(value = "/reservation/dates", params = "cancel")
    public String cancelDates(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/activities/list";
    }



    // Flow step 2 - add customer

    @GetMapping("/reservation/customer")
    public String getCustomerForm(@ModelAttribute("reservationFlow") ReservationFlow reservationFlow) {
        reservationFlow.enterStep(ReservationFlow.Step.Customer);
        return "reservation/customer";
    }

    @PostMapping(value = "/reservation/customer", params = "back")
    public String fromCustomerBackToDates(@ModelAttribute("reservationFlow") ReservationFlow reservationFlow,
                                       RedirectAttributes ra) {
        reservationFlow.enterStep(ReservationFlow.Step.Customer);
        ra.addFlashAttribute("reservationFlow", reservationFlow);
        return "redirect:/reservation?activityId=" + reservationFlow.getReservation().getActivity().getId();
    }

    @PostMapping(value = "/reservation/customer")
    public String postAddCustomer(@ModelAttribute("reservationFlow") ReservationFlow reservationFlow) {
        reservationFlow.enterStep(ReservationFlow.Step.Customer);
        return "redirect:/reservation/review";
    }



    // Flow step 3 - review reservation

    @GetMapping("/reservation/review")
    public String getReview(@ModelAttribute("reservationFlow") ReservationFlow reservationFlow,
                            RedirectAttributes ra) {

        reservationFlow.setActive(ReservationFlow.Step.Review);
        ra.addFlashAttribute("reservationFlow", reservationFlow);
        reservationFlow.getReservation().setPrice(reservationService.calculatePrice(reservationFlow.getReservation()));
        return "reservation/review";
    }

    @PostMapping(value = "/reservation/review", params = "back")
    public String fromReviewBackToCustomer(@ModelAttribute("reservationFlow") ReservationFlow reservationFlow,
                                            RedirectAttributes ra) {
        reservationFlow.setActive(ReservationFlow.Step.Review);
        ra.addFlashAttribute("reservationFlow", reservationFlow);
        return "redirect:/reservation/customer";
    }

    @PostMapping(value = "/reservation/review", params = "confirm")
    public String postReview(@ModelAttribute("reservationFlow") ReservationFlow reservationFlow,
                             RedirectAttributes ra) {
        reservationFlow.setActive(ReservationFlow.Step.Review);
        ra.addFlashAttribute("reservationFlow", reservationFlow);
        reservationService.save(reservationFlow.getReservation());
        reservationFlow.completeStep(ReservationFlow.Step.Review);
        return "redirect:/reservation/completed";
    }



    // End flow

    @GetMapping("/reservation/completed")
    public String getFlowCompleted(@ModelAttribute("reservationFlow") ReservationFlow reservationFlow, SessionStatus sessionStatus) {
        // setComplete() method only removes attributes set by the @SessionAttributes annotation
        // it removes the attributes after the page rendering
        sessionStatus.setComplete();
        return "reservation/completed";
    }

    //DElETE

    @RequestMapping(value="/reservation/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Long id){
        reservationService.cancel(id);
        return "redirect:/reservation/list";
    }
}
