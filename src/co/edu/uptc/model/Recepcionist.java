package co.edu.uptc.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Recepcionist extends User {
    private Parking parking;
    private ArrayList<Vehicle> attendedVehicles;

    public Recepcionist(String name, String lastName, String email, String phone, String address, String id,
            String password, Parking parking) {
        super(name, lastName, email, phone, address, id, password);
        this.parking = parking;
        attendedVehicles = new ArrayList<>();
    }

    public Recepcionist(String id) {
        super(id);
        this.parking = new Parking("Recepcionist Parking");
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public ArrayList<Vehicle> getAttendedVehicles() {
        return attendedVehicles;
    }

    public void setAttendedVehicles(ArrayList<Vehicle> attendedVehicles) {
        this.attendedVehicles = attendedVehicles;
    }

    public void registerEntryVehicle(String plate, String type) {
        parking.registerVehicle(plate, type, LocalTime.now());

    }

    public void registerVehicleExit(String plate) {
        attendedVehicles.add(parking.deleteVehicle(plate));
    }

    public Ticket generateTicket(String plate, double amountReceived) {
        return new Ticket(plate, parking.calculateCost(plate), amountReceived,
                parking.getPassedTime(parking.getVehicle(plate)));
    }

    public String seeParkingAvailability() {
        return parking.updateAvailability();
    }
}