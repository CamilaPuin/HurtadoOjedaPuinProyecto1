package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SystemParking {
    private ArrayList<Admin> admins;
    private ArrayList<Recepcionist> recepcionists;
    private Recepcionist currentRecepcionist;
    private Admin currentAdmin;

    public SystemParking() {
<<<<<<< HEAD
        Admin admin = new Admin("admin", "admin", "admin@admin.com", "123456789", "Calle de la casa, 1", "2",

                "2");
        admin.registerParking("Parking UPTC", "UPTC", "parkinguptc", 10, 10, LocalTime.now(), new ArrayList<>());
        Recepcionist recepcionist = new Recepcionist("recepcionist", "recepcionist", "recepcionist@recepcionist.com",
                "123456789",
                "Calle de la casa, 1", "1", "1", admin.getParking());
        Recepcionist recepcionistTwo = new Recepcionist("Sapopet", "Sacudeme la trompeta",
=======
        Admin admin = new Admin("admin", "admin", "admin@admin.com", "12345678", "Calle de la casa, 1", "admin",
                "12345678");
        admin.registerParking("Parking UPTC", "UPTC", "parkinguptc", 10, 10, LocalTime.now(), new ArrayList<>());
        Recepcionist recepcionist = new Recepcionist("recepcionist", "recepcionist", "recepcionist@recepcionist.com",
                "123456789",
                "Calle de la casa, 1", "recepcionist", "12345678", admin.getParking());
        Recepcionist recepcionistTwo = new Recepcionist("Sapopeta", "Sacudeme la trompeta",
>>>>>>> c99c69e040530b97f1933f840f99c706dc22bccf
                "recepcionist@recepcionist.com",
                "123456789",
                "Calle de la casa, 1", "1", "1", admin.getParking());
        recepcionists = new ArrayList<>();
        recepcionists.add(recepcionist);
        recepcionists.add(recepcionistTwo);
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
<<<<<<< HEAD
        Recepcionist newRecepcionist = currentAdmin.createRecepcionist(name, lastName, email, phone, address, id,
                generatePassword());
        recepcionists.add(newRecepcionist);
=======

        recepcionists
                .add(currentAdmin.createRecepcionist(name, lastName, email, phone, address, id, generatePassword()));
>>>>>>> c99c69e040530b97f1933f840f99c706dc22bccf
        recepcionists.sort(Comparator.comparing(Recepcionist::getId));
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

<<<<<<< HEAD
    public boolean updateRecepcionist(String id, String name, String address, String phone, String email,
            String password) {
        int index = searchRecepcionist(id);
        if (index >= 0) {
            Recepcionist recepcionist = recepcionists.get(index);
            recepcionist.setName(name);
            recepcionist.setAddress(address);
            recepcionist.setPhone(phone);
            recepcionist.setEmail(email);
            recepcionist.setPassword(password);
            return true;
        }
        return false;
    }

    public String getRecepcionistPassword(String id) {
        int index = searchRecepcionist(id);
        if (index >= 0) {
            return recepcionists.get(index).getPassword();
        }
        return null; 
=======
    public boolean updateRecepcionist(String id, String address, String phone, String email,
            String newPassword, String confirmPassword) {
        int index = searchRecepcionist(id);
        if (index >= 0) {
            Recepcionist recepcionist = recepcionists.get(index);
            recepcionist.setAddress(address);
            recepcionist.setPhone(phone);
            recepcionist.setEmail(email);
            if (checkPassword(recepcionist, confirmPassword, newPassword))
                recepcionist.setPassword(newPassword);
            return true;
        } else
            return false;

>>>>>>> c99c69e040530b97f1933f840f99c706dc22bccf
    }

    public int searchRecepcionist(String id) {
        return Collections.binarySearch(recepcionists, new Recepcionist(id),
                Comparator.comparing(Recepcionist::getId));
    }

<<<<<<< HEAD
=======
    private boolean checkPassword(Recepcionist recepcionist, String password, String passwordConfirm) {
        return (!recepcionist.getPassword().equals(password) && passwordConfirm.equals(password)
                && password.length() >= 8);
    }

>>>>>>> c99c69e040530b97f1933f840f99c706dc22bccf
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
        // Depuración
        System.out.println("SystemParking - Llamando a Recepcionist.registerEntryVehicle con:");
        System.out.println("Placa: " + plate);
        System.out.println("Tipo: " + type);
    
        currentRecepcionist.registerEntryVehicle(plate, type);
    }

    public void exitVehicle(String plate) {
        currentRecepcionist.registerVehicleExit(plate);

    }

    public String logIn(String id, String password) {
        String user = "";
        int index = Collections.binarySearch(admins, new Admin(id), Comparator.comparing(Admin::getId));
        if (index >= 0 && admins.get(index).getPassword().equals(password)) {
            currentAdmin = admins.get(index);
            user = "Administrador";
        } else {
            index = Collections.binarySearch(recepcionists, new Recepcionist(id),
                    Comparator.comparing(Recepcionist::getId));
            if (index >= 0 && recepcionists.get(index).getPassword().equals(password)) {
                currentRecepcionist = recepcionists.get(index);
                user = "Recepcionista";
            }
        }
        return user;
    }

    public double costTikect(String plate, double amountReceived) {
        return currentRecepcionist.getParking().calculateCost(plate);
    }

    public double calculteChange(String plate, double amountReceived) {
        return Ticket.calculateChange(amountReceived, costTikect(plate, amountReceived));
    }

    public int hoursVehicle(String plate) {
        return Parking.getPassedTime(currentRecepcionist.getParking().getVehicle(plate));
    }

    public String[] obtainRecepcionist(String id) {
        String[] recepcionistData = new String[4];
        int index = searchRecepcionist(id);
        if (index >= 0) {
            recepcionistData[0] = recepcionists.get(index).getName();
            recepcionistData[1] = recepcionists.get(index).getAddress();
            recepcionistData[2] = recepcionists.get(index).getPhone();
            recepcionistData[3] = recepcionists.get(index).getEmail();
            return recepcionistData;
        }
        return null;
    }

    public int numAttendedVehicles() {
        return currentRecepcionist.numAttendedVehicles();
    }

    public double income() {
        return currentRecepcionist.income();
    }

    public String[] obtainRecepcionistData() {
        String[] recepcionistData = { currentRecepcionist.getName(), currentRecepcionist.getLastName() };
        return recepcionistData;
    }

    public Object[][] getConsolidatedRecepcionists() {
        Object[][] data = new Object[recepcionists.size()][4];
        for (int i = 0; i < recepcionists.size(); i++) {
            Recepcionist r = recepcionists.get(i);
            data[i][0] = r.getName();
            data[i][1] = r.getLastName();
            data[i][2] = r.income();
            data[i][3] = r.numAttendedVehicles();
        }
        return data;
    }

<<<<<<< HEAD
=======
    public boolean foundedVehicle(String plate) {
        return currentRecepcionist.foundedVehicle(plate);
    }
>>>>>>> c99c69e040530b97f1933f840f99c706dc22bccf
}
