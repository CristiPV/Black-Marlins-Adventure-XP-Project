package com.blackmarlins.adventurexp.model.reservation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Stores the {@code Reservation} and the current flow {@code Step}. {@code Step} manipulation functions are dumb and
 * rely on controller logic to keep the flow step in sync.
 *
 * <p>There cannot be an incrementing counter given page refreshes must not advance flow steps.</p>
 */
public class ReservationFlow {

    public enum Step {
        Dates(0),
        Customer(1),
        Review(2);

        int flowStep;

        Step(int flowStep) {
            this.flowStep = flowStep;
        }

        public static Step from(int flowStep) {
            switch (flowStep) {
                case 0:
                    return Dates;
                case 1:
                    return Customer;
                case 2:
                    return Review;
                default:
                    return Dates;
            }
        }
    }

    private Reservation reservation = new Reservation();

    private List<StepDescription> stepDescriptions = new ArrayList<>();

    private Set<Step> completedSteps = new HashSet<>();

    private Step activeStep = Step.Dates;

    public ReservationFlow() {
        stepDescriptions.add(new StepDescription(0, "Dates", "Choose date and duration of the activity"));
        stepDescriptions.add(new StepDescription(1, "Customer", "Provide customer details"));
        stepDescriptions.add(new StepDescription(2, "Review", "Verify your reservation"));
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        reservation.setPrice(reservation.getActivity().getHourlyPrice() *
                reservation.getAmountOfPeople() * reservation.getHours());
        this.reservation = reservation;
    }

    public void setActive(Step step) {
        activeStep = step;
    }

    public Step getActiveStep() {
        return activeStep;
    }

    public StepDescription getActiveStepDescription() {
        return stepDescriptions.get(activeStep.flowStep);
    }

    public void completeStep(Step step) {
        completedSteps.add(step);
    }

    public void incompleteStep(Step step) {
        completedSteps.remove(step);
    }

    public boolean isActive(Step step) {
        return step == activeStep;
    }

    public boolean isCompleted(Step step) {
        return completedSteps.contains(step);
    }

    public void enterStep(Step step) {
        setActive(step);
        incompleteStep(step);
    }

    public List<StepDescription> getStepDescriptions() {
        return stepDescriptions;
    }

    public static class StepDescription {
        private int flowStep;
        private String title;
        private String description;

        public StepDescription(int flowStep, String title, String description) {
            this.flowStep = flowStep;
            this.title = title;
            this.description = description;
        }

        public int getFlowStep() {
            return flowStep;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        private int normalizedFlowStep() {
            return flowStep + 1;
        }

        public String getFlowStepWithTitle() {
            return normalizedFlowStep() + ". " + title;
        }

        public String getFlowStepWithDescription() {
            return normalizedFlowStep() + ". " + description;
        }

        @Override
        public String toString() {
            return "StepDescription{" +
                    "flowStep=" + flowStep +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ReservationFlow{" +
                "reservation=" + reservation +
                ", stepDescriptions=" + stepDescriptions +
                ", completedSteps=" + completedSteps +
                ", activeStep=" + activeStep +
                '}';
    }
}


