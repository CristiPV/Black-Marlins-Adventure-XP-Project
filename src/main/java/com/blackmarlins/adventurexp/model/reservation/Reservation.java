package com.blackmarlins.adventurexp.model.reservation;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.model.Customer;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

   // public static final double TAX_PERCENTAGE = 0.20;



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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date = LocalDateTime.now();
    @Column(nullable = false)
    private int hours = 1;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date2;
    @Column(nullable = false)
    private int amountOfPeople = 1;
    @Column
    private double price;

    public Reservation() {
        this.id = Long.valueOf(0);
    }

    public Reservation(Activity activity, Customer customer, LocalDateTime date, int hours, int amountOfPeople) {
        this.activity = activity;
        this.customer = customer;
        this.date = date;
        this.hours = hours;
        this.amountOfPeople = amountOfPeople;
    }


    /*public static double getTaxPercentage() {
        return TAX_PERCENTAGE;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /*public Double getTotalPrice() {
        return (this.hours * this.activity.getHourlyPrice()) * (1 + TAX_PERCENTAGE);
    }*/

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public LocalDateTime getDate2() {
        return date2;
    }

    public void setDate2() {
        this.date2 = this.date.plusHours(this.hours);
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customer=" + customer +
                ", date=" + date +
                ", hours=" + hours +
                ", amountOfPeople=" + amountOfPeople +
                ", price=" + price +
                '}';
    }
}
