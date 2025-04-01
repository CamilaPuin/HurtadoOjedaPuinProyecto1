package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Admin extends User {
    private ArrayList<Parking> parkings = new ArrayList<Parking>();
    private ArrayList<Recepcionist> recepcionists = new ArrayList<Recepcionist>();

    public Admin(String name, String lastName, String email, String phone, String address, String id) {
        super(name, lastName, email, phone, address, id);
    }

    public ArrayList<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(ArrayList<Parking> parkings) {
        this.parkings = parkings;
    }

    public ArrayList<Recepcionist> getRecepcionists() {
        return recepcionists;
    }

    public void setRecepcionists(ArrayList<Recepcionist> recepcionists) {
        this.recepcionists = recepcionists;
    }

    public void registerParking(String name, String address, String id, int carsCapacity, int motorbikesCapacity,
            LocalTime openingHour, ArrayList<Recepcionist> parkingRecepcionists) {
        parkings.add(new Parking(name, address, id, carsCapacity, motorbikesCapacity, openingHour, new ArrayList<>(),
                new ArrayList<>(), parkingRecepcionists));
    }

    public void createRecepcionist(String name, String lastName, String email, String phone, String address, String id) {
        recepcionists.add(new Recepcionist(name, lastName, email, phone, address, id));
    }

    // TODO update?
    public void updateRecepcionistData(String name, String lastName, String email, String phone, String address,
            String id,
            String parking) {
        int index = searchRecepcionist(id);
        if (name != null)
            recepcionists.get(index).setName(name);
        if (lastName != null)
            recepcionists.get(index).setLastName(lastName);
        if (email != null)
            recepcionists.get(index).setEmail(email);
        if (phone != null)
            recepcionists.get(index).setPhone(phone);
        if (address != null)
            recepcionists.get(index).setAddress(address);
        if (parking != null)
            recepcionists.get(index).setParking(parkings.get(searchParking(parking)));
    }

    // TODO add parking to wifreframe?
    public ArrayList<String> generateSalesReport(String Parking, LocalDate date) {
        Parking parking = parkings.get(searchParking(Parking));
        ArrayList<String> salesReport = new ArrayList<>();
        for (Recepcionist recepcionist : recepcionists) {
            if (recepcionist.getParking().equals(parking)) {
                // TODO: date management, arrays?
            }
        }
        return null;

    }

    public int searchRecepcionist(String id) {
        return Collections.binarySearch(recepcionists, new Recepcionist(id), Comparator.comparing(Recepcionist::getId));
    }

    public int searchParking(String name) {
        return Collections.binarySearch(parkings, new Parking(name), Comparator.comparing(Parking::getName));
    }
}