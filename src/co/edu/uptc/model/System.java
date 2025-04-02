package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class System {
    private ArrayList<Admin> admins;
    private ArrayList<Recepcionist> recepcionists;
    private Admin currentAdmin;
    private Recepcionist currentRecepcionist;

    public System() {
        Admin admin = new Admin("admin", "admin", "admin@admin.com", "123456789", "Calle de la casa, 1", "admin",
                "12345678");
        admin.registerParking("Parking UPTC", "UPTC", "parkinguptc", 10, 10, LocalTime.now(), new ArrayList<>());
        Recepcionist recepcionist = new Recepcionist("recepcionist", "recepcionist", "recepcionist@recepcionist.com",
                "123456789",
                "Calle de la casa, 1", "recepcionist", "12345678",admin.getParking());
        recepcionists = new ArrayList<>();
        recepcionists.add(recepcionist);
        admins = new ArrayList<>();
        admins.add(admin);
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public ArrayList<Recepcionist> getRecepcionists() {
        return recepcionists;
    }

    public void setRecepcionists(ArrayList<Recepcionist> recepcionists) {
        this.recepcionists = recepcionists;
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentAdmin(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    public Recepcionist getCurrentRecepcionist() {
        return currentRecepcionist;
    }

    public void setCurrentRecepcionist(Recepcionist currentRecepcionist) {
        this.currentRecepcionist = currentRecepcionist;
    }

    public void createRecepcionist(String name, String lastName, String email, String phone, String address,
            String id, String password) {
        currentAdmin.createRecepcionist(name, lastName, email, phone, address, id, password);
    }
    //search recepcionist by id
    public void updateRecepcionist( String email, String phone, String address,
            String id,String password) {
        int index = Collections.binarySearch(recepcionists, new Recepcionist(id), Comparator.comparing(Recepcionist::getId));
        if (index >= 0)
            currentAdmin.updateRecepcionistData( email, phone, address, id, recepcionists.get(index),password);
    }

    public void salesReport(LocalDate date) {
        currentAdmin.generateSalesReport(date);
    }

    public void logout() {
        currentAdmin = null;
        currentRecepcionist = null;
    }

    public void availableSpaces() {
        currentRecepcionist.seeParkingAvailability();
    }

    public void registerVehicle(String plate, String type) {
        currentRecepcionist.registerEntryVehicle(plate,type);

    }

    public void exitVehicle(String plate) {
        currentRecepcionist.registerVehicleExit(plate);
    }

    public boolean logIn(String id, String password, String userType) {
        if (userType.equalsIgnoreCase("Administrador")) {
            int index = Collections.binarySearch(admins, new Admin(id), Comparator.comparing(Admin::getId));
            if (index >= 0) {
                if (admins.get(index).getPassword().equals(password)) {
                    currentAdmin = admins.get(index);
                    return true;
                }
                return false; 
            }
            return false; 
        } 
        else if (userType.equalsIgnoreCase("Recepcionista")) {
            int index = Collections.binarySearch(recepcionists, new Recepcionist(id), Comparator.comparing(Recepcionist::getId));
            if (index >= 0) {
                if (recepcionists.get(index).getPassword().equals(password)) {
                    currentRecepcionist = recepcionists.get(index);
                    return true;
                }
                return false; 
            }
            return false; 
        }
        return false;
    }
    

}
