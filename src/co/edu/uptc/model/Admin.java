package co.edu.uptc.model;

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

    public Recepcionist createRecepcionist(String id) {
        return new Recepcionist(id);
    }

    public void updateRecepcionistData(String email, String phone, String address,  String password, Recepcionist recepcionist) {
            recepcionist.setPhone(phone);
            recepcionist.setAddress(address);
            recepcionist.setPassword(password);
    }
   
}