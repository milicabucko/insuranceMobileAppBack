package com.travelinsurance.insurance.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Destination implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private Double pricePersonPerDay;

    private Double priceVehiclePerDay;

    public Destination() {
    }

    public Destination(String country, Double pricePersonPerDay, Double priceVehiclePerDay) {
        this.country = country;
        this.pricePersonPerDay = pricePersonPerDay;
        this.priceVehiclePerDay = priceVehiclePerDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getPricePersonPerDay() {
        return pricePersonPerDay;
    }

    public void setPricePersonPerDay(Double pricePersonPerDay) {
        this.pricePersonPerDay = pricePersonPerDay;
    }

    public Double getPriceVehiclePerDay() {
        return priceVehiclePerDay;
    }

    public void setPriceVehiclePerDay(Double priceVehiclePerDay) {
        this.priceVehiclePerDay = priceVehiclePerDay;
    }
}
