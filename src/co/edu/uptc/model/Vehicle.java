package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vehicle {
    private String plate;
    private String type;
    private LocalTime entryTime;
    private LocalDate date;
    private boolean isRegistered;


    public Vehicle(String plate, String type, LocalTime entryTime, boolean isRegistered) {
        this.plate = plate;
        this.type = type;
        this.entryTime = entryTime;
        this.date = LocalDate.now();
        this.isRegistered = isRegistered;
    }

    public Vehicle(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}