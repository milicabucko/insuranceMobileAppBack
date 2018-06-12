package com.travelinsurance.insurance.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
public class Insurance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    private int numOfPeople;

    private boolean isApproved;

    @OneToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private User salesman;

    @OneToMany
    @JoinColumn
    private Collection<User> insuredUsers;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;


    public Insurance() {
    }

    public Insurance(Date fromDate, Date toDate, int numOfPeople, boolean isApproved, User buyer, User salesman, Collection<User> insuredUsers) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numOfPeople = numOfPeople;
        this.isApproved = isApproved;
        this.buyer = buyer;
        this.salesman = salesman;
        this.insuredUsers = insuredUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSalesman() {
        return salesman;
    }

    public void setSalesman(User salesman) {
        this.salesman = salesman;
    }

    public Collection<User> getInsuredUsers() {
        return insuredUsers;
    }

    public void setInsuredUsers(Collection<User> insuredUsers) {
        this.insuredUsers = insuredUsers;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
