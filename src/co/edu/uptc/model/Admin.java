package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Admin extends User {
    Parking parking;

    public Admin(String name, String lastName, String email, String phone, String address, String id, String password) {
        super(name, lastName, email, phone, address, id, password);
        parking = registerParking("Parking UPTC", "UPTC", "parkinguptc", 10, 10, LocalTime.now(), new ArrayList<>());
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
        return new Recepcionist(name, lastName, email, phone, address, id, password);
    }

    public void updateRecepcionistData(String name, String lastName, String email, String phone, String address,
            String id, Recepcionist recepcionist, String password) {
        if (name != null)
            recepcionist.setName(name);
        if (lastName != null)
            recepcionist.setLastName(lastName);
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
        return null;
    }

}