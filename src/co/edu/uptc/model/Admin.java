package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Admin extends User {
    Parking parking;

    public Admin(String name, String lastName, String email, String phone, String address, String id, String password) {
        super(name, lastName, email, phone, address, id, password);
        parking = new Parking("Parking UPTC", "UPTC", "parkinguptc", 10, 10, LocalTime.now(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
    }

    public Admin(String id) {
        super(id);
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Parking registerParking(String name, String address, String id, int carsCapacity, int motorbikesCapacity,
            LocalTime openingHour, ArrayList<Recepcionist> parkingRecepcionists) {
        return new Parking(name, address, id, carsCapacity, motorbikesCapacity, openingHour, new ArrayList<>(),
                new ArrayList<>(), parkingRecepcionists);
    }

    public Recepcionist createRecepcionist(String name, String lastName, String email, String phone, String address,
            String id, String password) {
        return new Recepcionist(name, lastName, email, phone, address, id, password, parking);
    }

    public void updateRecepcionistData(String email, String phone, String address,
            String id, Recepcionist recepcionist, String password) {
        if (email != null)
            recepcionist.setEmail(email);
        if (phone != null)
            recepcionist.setPhone(phone);
        if (address != null)
            recepcionist.setAddress(address);
        if (password != null)
            recepcionist.setPassword(password);
    }

    public ArrayList<String> generateSalesReport(LocalDate date) {

        ArrayList<String> report = new ArrayList<>();
        report.add("Nombre,Apellidos,Dinero,Vehículos,Motocicletas,Coches");
        int totalMoney = 0;
        int totalVehicles = 0;
        int totalMotorbikes = 0;
        int totalCars = 0;
        for (Recepcionist recepcionist : parking.getRecepcionists()) {
            int money = 0;
            int vehicles = 0;
            int motorbikes = 0;
            int cars = 0;
            for (Vehicle vehicle : recepcionist.getAttendedVehicles()) {
                if (vehicle.getDate().equals(date)) {
                    money += parking.calculateCost(vehicle.getPlate());
                    vehicles++;
                    if (vehicle.getType().equals("motorbike"))
                        motorbikes++;
                    if (vehicle.getType().equals("car"))
                        cars++;
                }
                report.add(recepcionist.getName() + "," + recepcionist.getLastName() + "," + money + "," + vehicles
                        + "," + motorbikes + "," + cars);
            }
            totalMoney += money;
            totalVehicles += vehicles;
            totalMotorbikes += motorbikes;
            totalCars += cars;
        }
        report.add("Total: " + totalMoney + ", Vehículos: " + totalVehicles + ", Motocicletas: " + totalMotorbikes
                + ", Coches: " + totalCars);
        return report;

    }

}