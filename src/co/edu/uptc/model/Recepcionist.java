package co.edu.uptc.model;

import java.time.LocalTime;

import co.edu.uptc.model.Parking;

public class Recepcionist extends User {
    private Parking parking;
    private double money;
    private int registeredVehicles;

    public Recepcionist(String name, String lastName, String email, String phone, String address, String id,
            Parking parking) {
        super(name, lastName, email, phone, address, id);
        this.parking = parking;
    }

    public Recepcionist(String id) {
        super(id);
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getRegisteredVehicles() {
        return registeredVehicles;
    }

    public void setRegisteredVehicles(int registeredVehicles) {
        this.registeredVehicles = registeredVehicles;
    }

    public void registerEntryVehicle(String plate, String type) {
        parking.registerVehicle(plate, type, LocalTime.now());
        registeredVehicles++;
    }

    public void registerVehicleExit(String plate) {
        parking.deleteVehicle(plate);
    }

    // TODO static methods and params
    public Ticket generateTicket(String plate, double amountReceived) {
        return Ticket.generateTicket(plate, parking.calculateCost(plate), amountReceived,
                parking.getPassedTime(parking.getVehicle(plate)));
    }

    public String seeParkingAvailability() {
        return parking.updateAvailability();
    }

    @Override
    public String toString() {
        return "Recepcionist [parking=" + parking + ", money=" + money + ", registeredVehicles=" + registeredVehicles
                + "]";
    }

}