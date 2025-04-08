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

    public int numAttendedVehicles() {
        return getAttendedVehicles().size();
    }

    public void setAttendedVehicles(ArrayList<Vehicle> attendedVehicles) {
        this.attendedVehicles = attendedVehicles;
    }

    public void registerEntryVehicle(String plate, String type) {
        parking.registerVehicle(plate, type, LocalTime.now());

    }

    public void registerVehicleExit(String plate) {
        Vehicle vehicle = parking.deleteVehicle(plate);
        if (vehicle != null) {
            attendedVehicles.add(vehicle);
        } else {
            System.out.println("El vehículo con la placa " + plate + " no existe en el estacionamiento.");
        }
    }

    public Ticket generateTicket(String plate, double amountReceived) {
        return new Ticket(plate, parking.calculateCost(plate), amountReceived,
                parking.getPassedTime(parking.getVehicle(plate)));
    }

    public String seeParkingAvailability() {
        return parking.updateAvailability();
    }

    public double income() {
        double totalIncome = 0;
        for (Vehicle vehicle : attendedVehicles) {
            if (vehicle != null) { // Verifica que el vehículo no sea null
                totalIncome += parking.calculateCost(vehicle.getPlate());
            }
        }
        return totalIncome;
    }

    public void setName(String name) {
        super.setName(name); // Llama al método setName de la clase base
    }

    public void setAddress(String address) {
        super.setAddress(address);
    }

    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

}