package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Vehicle {
    private String plate;
    private String type;
    private LocalTime entryTime;
    private LocalDate date;

    public Vehicle(String plate, String type, LocalTime entryTime) {
        this.plate = plate;
        this.type = type;
        this.entryTime = entryTime;
        this.date = LocalDate.now();
    }

    public Vehicle(String plate) {
        this.plate = plate;
    }

    public Vehicle(LocalDate date) {
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "El vehículo de tipo '" + type + "' con placa '" + plate + "' se ha registrado correctamente.";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Vehicle vehicle = (Vehicle) obj;
        return plate.equals(vehicle.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plate);
    }

}