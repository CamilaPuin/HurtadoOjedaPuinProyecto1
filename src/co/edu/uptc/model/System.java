package co.edu.uptc.model;

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
                "Calle de la casa, 1", "recepcionist", "12345678");
        // TODO set parking?
        recepcionist.setParking(admin.getParkings().get(0));
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

    public void adminregisterparking() {

    }

    public void createRecepcionist() {

    }

    public void updateRecepcionist() {
    }

    public void salesReport() {
    }

    public void logout() {
        currentAdmin = null;
        currentRecepcionist = null;
    }

    
    public void availableSpaces() {
    }
    public void registerVehicle() {

    }
    public void exitVehicle() {

    }

}
