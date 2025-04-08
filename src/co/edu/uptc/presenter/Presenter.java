package co.edu.uptc.presenter;

import co.edu.uptc.model.SystemParking;
import co.edu.uptc.view.View;
import java.time.LocalDate;

public class Presenter {
    private SystemParking system;
    private View view;

    public Presenter(View view) {
        this.view = view;
        this.system = new SystemParking();
    }

    public void createRecepcionist(String name, String lastName, String email, String phone, String address,
            String id) {
        system.createRecepcionist(name, lastName, email, phone, address, id);
    }

    public boolean updateRecepcionist(String id, String name, String address, String phone, String email,
            String newPassword, String confirmPassword) {
        String oldPassword = system.getRecepcionistPassword(id);
        if (!newPassword.equals(confirmPassword)) {
            view.showErrorMessage("Las contraseñas no coinciden.");
            return false;
        }
        if (!validatePassword(newPassword, oldPassword))
            return false;
        return system.updateRecepcionist(id, name, address, phone, email, newPassword);
    }

    public void salesReport(LocalDate date) {
        system.salesReport(date);
    }

    public void logout() {
        system.logout();
    }

    public String availableSpaces() {
        return system.availableSpaces();
    }

    public void registerVehicle(String plate, String type) {
        // Depuración
        System.out.println("Presenter - Llamando a SystemParking.registerVehicle con:");
        System.out.println("Placa: " + plate);
        System.out.println("Tipo: " + type);
    
        system.registerVehicle(plate, type);
    }

    public void exitVehicle(String plate) {
        system.exitVehicle(plate);
    }

    public String logIn(String addrees, String password) {
        return system.logIn(addrees, password);
    }

    public String getFullName(String id) {
        return system.getFullName(id);
    }

    public String[] obtainRecepcionist(String id) {
        return system.obtainRecepcionist(id);
    }

    public double costTikect(String plate, double amountReceived) {
        return system.costTikect(plate, amountReceived);
    }

    public double calculteChange(String plate, double amountReceived) {
        return system.calculteChange(plate, amountReceived);
    }

    public int hoursVehicle(String plate) {
        return system.hoursVehicle(plate);
    }

    public int numAttendedVehicles() {
        return system.numAttendedVehicles();
    }

    public double income() {
        return system.income();
    }

    public String[] obtainRecepcionistData() {
        return system.obtainRecepcionistData();
    }

    public Object[][] getConsolidatedRecepcionists() {
        return system.getConsolidatedRecepcionists();
    }

    public boolean validatePassword(String newPassword, String oldPassword) {
        if (newPassword.equals(oldPassword)) {
            view.showErrorMessage("La nueva contraseña no puede ser igual a la anterior.");
            return false;
        }
        if (newPassword.length() < 8) {
            view.showErrorMessage("La contraseña debe tener al menos 8 caracteres.");
            return false;
        }
        if (!newPassword.matches("^[a-zA-Z0-9]+$")) {
            view.showErrorMessage("La contraseña no debe contener caracteres especiales.");
            return false;
        }
        return true;
    }

    public boolean foundedVehicle(String plate) {
        return system.foundedVehicle(plate);
    }

}