package co.edu.uptc.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Admin extends User {
    private ArrayList<Parking> parkings = new ArrayList<Parking>();

    public Admin(String name, String lastName, String email, String phone, String address, String id, String password) {
        super(name, lastName, email, phone, address, id, password);
    }

    public ArrayList<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(ArrayList<Parking> parkings) {
        this.parkings = parkings;
    }

    public void registerParking(String name, String address, String id, int carsCapacity, int motorbikesCapacity,
            LocalTime openingHour, ArrayList<Recepcionist> parkingRecepcionists) {
        parkings.add(new Parking(name, address, id, carsCapacity, motorbikesCapacity, openingHour, new ArrayList<>(),
                new ArrayList<>(), parkingRecepcionists));
    }

    public Recepcionist createRecepcionist(String name, String lastName, String email, String phone, String address,
            String id, String password) {
        return new Recepcionist(name, lastName, email, phone, address, id, password);
    }

    // TODO update?
    public void updateRecepcionistData(String name, String lastName, String email, String phone, String address,
            String id,
            Parking parking, Recepcionist recepcionists) {
        if (name != null)
            recepcionists.setName(name);
        if (lastName != null)
            recepcionists.setLastName(lastName);
        if (email != null)
            recepcionists.setEmail(email);
        if (phone != null)
            recepcionists.setPhone(phone);
        if (address != null)
            recepcionists.setAddress(address);
        if (parking != null)
            recepcionists.setParking(parking);
    }

    // TODO add parking to wifreframe?
    // public ArrayList<String> generateSalesReport(String Parking, LocalDate date)
    // {
    // Parking parking = parkings.get(searchParking(Parking));
    // ArrayList<String> salesReport = new ArrayList<>();
    // for (Recepcionist recepcionist : recepcionists) {
    // if (recepcionist.getParking().equals(parking)) {
    // // TODO: date management, arrays?
    // }
    // }
    // return null;

    // }


    public int searchParking(String name) {
        return Collections.binarySearch(parkings, new Parking(name), Comparator.comparing(Parking::getName));
    }
}