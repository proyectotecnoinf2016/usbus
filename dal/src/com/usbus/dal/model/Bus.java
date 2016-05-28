package com.usbus.dal.model;

import com.usbus.commons.enums.BusStatus;
import com.usbus.dal.BaseEntity;

/**
 * Created by Lufasoch on 28/05/2016.
 */
public class Bus extends BaseEntity{
    private String id;
    private String brand;
    private String model;
    private Double kms;
    private Double nextMaintenance;
    private BusStatus status;
    private Boolean active;
    private Integer seats;
    private Integer trunkMaxWeight;
    private Integer standingPassengers;

    public Bus(){
    }

    public Bus(long tenantId, String id, String brand, String model, Double kms, Double nextMaintenance, BusStatus status, Boolean active, Integer seats, Integer trunkMaxWeight, Integer standingPassengers, Boolean active1) {
        super(tenantId);
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.kms = kms;
        this.nextMaintenance = nextMaintenance;
        this.status = status;
        this.active = active;
        this.seats = seats;
        this.trunkMaxWeight = trunkMaxWeight;
        this.standingPassengers = standingPassengers;
        this.active = active1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getKms() {
        return kms;
    }

    public void setKms(Double kms) {
        this.kms = kms;
    }

    public Double getNextMaintenance() {
        return nextMaintenance;
    }

    public void setNextMaintenance(Double nextMaintenance) {
        this.nextMaintenance = nextMaintenance;
    }

    public BusStatus getStatus() {
        return status;
    }

    public void setStatus(BusStatus status) {
        this.status = status;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getTrunkMaxWeight() {
        return trunkMaxWeight;
    }

    public void setTrunkMaxWeight(Integer trunkMaxWeight) {
        this.trunkMaxWeight = trunkMaxWeight;
    }

    public Integer getStandingPassengers() {
        return standingPassengers;
    }

    public void setStandingPassengers(Integer standingPassengers) {
        this.standingPassengers = standingPassengers;
    }
}