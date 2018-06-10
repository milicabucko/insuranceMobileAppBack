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

    @OneToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @JoinColumn
    private User salesman;

    @OneToMany
    @JoinColumn
    private Collection<User> insuredUsers;

    //private Vehicle vehicle;


    public Insurance() {
    }

    public Insurance(Date fromDate, Date toDate, int numOfPeople, User buyer, User salesman, Collection<User> insuredUsers) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numOfPeople = numOfPeople;
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
}
