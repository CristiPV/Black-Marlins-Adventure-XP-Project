package com.blackmarlins.adventurexp.model.reservation;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.model.Customer;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {

    public static final double TAX_PERCENTAGE = 0.20;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @Embedded
    private Customer customer = new Customer();
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date = LocalDate.now();
    @Column(nullable = false)
    private int hours = 1;

    public Reservation() {
    }

    public static double getTaxPercentage() {
        return TAX_PERCENTAGE;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        activity.getReservations().add(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return (this.hours * this.activity.getHourlyPrice()) * (1 + TAX_PERCENTAGE);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customer=" + customer +
                ", date=" + date +
                ", hours=" + hours +
                '}';
    }
}
