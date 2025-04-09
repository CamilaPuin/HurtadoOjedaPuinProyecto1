package co.edu.uptc.presenter;

import co.edu.uptc.model.SystemParking;
import co.edu.uptc.model.Exceptions.DateVehicleNotFoundException;
import co.edu.uptc.view.View;
import java.time.LocalDate;
import javax.swing.JOptionPane;

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

    public boolean updateRecepcionist(String id, String address, String phone, String email,
            String newPassword, String confirmPassword) {
        String oldPassword = system.getRecepcionistPassword(id);
        if (newPassword.isEmpty() && confirmPassword.isEmpty())
            return system.updateRecepcionist(id, address, phone, email, oldPassword);
        else if (!validatePassword(newPassword, confirmPassword, oldPassword))
            return false;
        return system.updateRecepcionist(id, address, phone, email, newPassword);
    }

    public void logout() {
        system.logout();
    }

    public String availableSpaces() {
        return system.availableSpaces();
    }

    public String registerVehicle(String plate, String type) {
        String result = system.registerVehicle(plate, type);
        if (result.contains("No hay espacio disponible")) {
            return result; 
        }
        return result;
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

    public Object[][] getConsolidatedRecepcionists(LocalDate date) throws DateVehicleNotFoundException {
        return system.getConsolidatedRecepcionists(date);
    }

    public void showErrorMessage(String message) {
        view.showErrorMessage(message);
    }

    public boolean validatePassword(String newPassword, String confirmPassword, String oldPassword) {
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
        if (!newPassword.equals(confirmPassword)) {
            view.showErrorMessage("Las contraseñas no coinciden.");
            return false;
        }
        return true;
    }

    public boolean foundedVehicle(String plate) {
        return system.foundedVehicle(plate);
    }

    public double totalMoney(LocalDate date) {
        return system.totalMoney(date);
    }
    public int totalVehicles(LocalDate date) {
        return system.totalVehicles(date);
    }

    public SystemParking getSystem() {
        return system;
    }

    public void setSystem(SystemParking system) {
        this.system = system;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
    
}