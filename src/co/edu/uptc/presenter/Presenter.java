package co.edu.uptc.presenter;

import co.edu.uptc.model.SystemParking;
import java.time.LocalDate;

public class Presenter {
    private SystemParking system;

    public Presenter() {
        system = new SystemParking();

    }

    public void createRecepcionist(String name, String lastName, String email, String phone, String address,
            String id) {
        system.createRecepcionist(name, lastName, email, phone, address, id);
    }

    public boolean updateRecepcionist(String id, String name, String address, String phone, String email,
            String password) {
        return system.updateRecepcionist(id, name, address, phone, email, password);
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

}