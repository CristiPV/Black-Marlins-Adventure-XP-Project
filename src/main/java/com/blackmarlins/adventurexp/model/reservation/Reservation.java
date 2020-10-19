package com.blackmarlins.adventurexp.model.reservation;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.model.Customer;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

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
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date =  new Date(System.currentTimeMillis());
    @Column(nullable = false)
    private int hours = 1;
    @Column(nullable = false)
    private int amountOfPeople = 1;
    @Column
    private double price;
    @Column
    private Boolean isCancelled;

    public Reservation() {
        this.id = Long.valueOf(0);
    }

    public Reservation(Long id, Activity activity, Customer customer, Date date, int hours, int amountOfPeople, double price, Boolean isCancelled) {
        this.id = id;
        this.activity = activity;
        this.customer = customer;
        this.date = date;
        this.hours = hours;
        this.amountOfPeople = amountOfPeople;
        this.price = price;
        this.isCancelled = isCancelled;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   /* public Double getTotalPrice() {
        return (this.hours * this.activity.getHourlyPrice()) * (1 + TAX_PERCENTAGE);
    }*/

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
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

    /*
        This setter calculates and sets the total price for a new Reservation.
        If the activity price updated, then the reservation price remains unchanged.
    */
    public void setPrice(double price) {
        this.price = hours * this.getActivity().getHourlyPrice() * amountOfPeople;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", activity_name=" + getActivity().getName() +
                ", customer=" + customer +
                ", date=" + date +
                ", hours=" + hours +
                ", amountOfPeople=" + amountOfPeople +
                ", price=" + price +
                '}';
    }
}
