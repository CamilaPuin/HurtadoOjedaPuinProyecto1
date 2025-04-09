package co.edu.uptc.model;

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

    public String registerEntryVehicle(String plate, String type) {
        return parking.registerVehicle(plate, type);
    }

    public void registerVehicleExit(String plate) {
        Vehicle vehicle = parking.deleteVehicle(plate);
        if (vehicle != null)
            attendedVehicles.add(vehicle);

    }

    public Ticket generateTicket(String plate, double amountReceived) {
        return new Ticket(plate, calculateCost(plate), amountReceived,
                Parking.getPassedTime(parking.getVehicle(plate)));
    }

    public String seeParkingAvailability() {
        return parking.updateAvailability();
    }

    public double calculateCost(String plate) {
        Vehicle vehicle = parking.getVehicle(plate);
        if (vehicle != null) {
            double costPerHour = "car".equals(vehicle.getType()) ? 2000 : 1000;
            vehicle.setCost(costPerHour * parking.getPassedTime(vehicle));
            return costPerHour * parking.getPassedTime(vehicle);
        }
        return 0;
    }

    public double income() {
        double totalIncome = 0;
        for (Vehicle vehicle : attendedVehicles) {
            if (vehicle != null) {
                totalIncome += calculateCost(vehicle.getPlate());
            }
        }
        return totalIncome;
    }

    public boolean foundedVehicle(String plate) {
        return parking.foundedVehicle(plate);
    }
}