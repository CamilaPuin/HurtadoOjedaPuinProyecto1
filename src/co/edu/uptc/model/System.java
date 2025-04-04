package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class System {
    private ArrayList<Admin> admins;
    private ArrayList<Recepcionist> recepcionists;
    private Admin currentAdmin;
    private Recepcionist currentRecepcionist;

    public System() {
        Admin admin = new Admin("admin", "admin", "admin@admin.com", "123456789", "Calle de la casa, 1", "123",
                "12345678");
        admin.registerParking("Parking UPTC", "UPTC", "parkinguptc", 10, 10, LocalTime.now(), new ArrayList<>());
        Recepcionist recepcionist = new Recepcionist("recepcionist", "recepcionist", "recepcionist@recepcionist.com",
                "123456789",
                "Calle de la casa, 1", "456", "12345678", admin.getParking());
        recepcionists = new ArrayList<>();
        recepcionists.add(recepcionist);
        recepcionists.sort(Comparator.comparing(Recepcionist::getId));
        admins = new ArrayList<>();
        admins.sort(Comparator.comparing(Admin::getId));
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

    public String getFullName(String id) {
        int index = searchRecepcionist(id);
        if (index >= 0) {
            return recepcionists.get(index).getName() + " " + recepcionists.get(index).getLastName();
        }
        return "";
    }

    public void createRecepcionist(String name, String lastName, String email, String phone, String address,
            String id) {
        currentAdmin.createRecepcionist(name, lastName, email, phone, address, id, generatePassword());
    }

    private String generatePassword() {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();

    }

    public void updateRecepcionist(String email, String phone, String address,
            String id, String password, String passwordConfirm) {
        int index = searchRecepcionist(id);
        if (index >= 0) {
            if (password != null && passwordConfirm != null) {
                if (checkPassword(index, password, passwordConfirm))
                    currentAdmin.updateRecepcionistData(email, phone, address, id, recepcionists.get(index), password);
            }
            currentAdmin.updateRecepcionistData(email, phone, address, id, recepcionists.get(index), password);
        }
    }

    public int searchRecepcionist(String id) {
        return Collections.binarySearch(recepcionists, new Recepcionist(id),
                Comparator.comparing(Recepcionist::getId));
    }

    private boolean checkPassword(int index, String password, String passwordConfirm) {
        return (!recepcionists.get(index).getPassword().equals(password) && passwordConfirm.equals(password)
                && password.length() >= 8);
    }

    public void salesReport(LocalDate date) {
        currentAdmin.generateSalesReport(date);
    }

    public void logout() {
        currentAdmin = null;
        currentRecepcionist = null;
    }

    public String availableSpaces() {
        return currentRecepcionist.seeParkingAvailability();
    }

    public void registerVehicle(String plate, String type) {
        currentRecepcionist.registerEntryVehicle(plate, type);

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
        } else if (userType.equalsIgnoreCase("Recepcionista")) {
            int index = Collections.binarySearch(recepcionists, new Recepcionist(id),
                    Comparator.comparing(Recepcionist::getId));
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
