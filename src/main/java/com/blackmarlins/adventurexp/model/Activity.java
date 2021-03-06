package com.blackmarlins.adventurexp.model;

import com.blackmarlins.adventurexp.model.reservation.Reservation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "activity", orphanRemoval=true)
    private List<Reservation> reservations;

    private String name;
    private String description;
    private Double hourlyPrice;
    private int ageLimit;
    private boolean isPaused;

    public Activity() {
        this.id = Long.valueOf(0);
        this.reservations = new ArrayList<>();
    }

    public List<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Activity(String name, String description, Double price, int ageLimit) {
        this.name = name;
        this.description = description;
        this.hourlyPrice = price;
        this.ageLimit = ageLimit;
        this.isPaused = false;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getHourlyPrice() {
        return hourlyPrice;
    }

    public void setHourlyPrice(Double price) {
        this.hourlyPrice = price;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", reservations=" + reservations +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hourlyPrice=" + hourlyPrice +
                ", ageLimit=" + ageLimit +
                ", isPaused=" + isPaused +
                '}';
    }
}
