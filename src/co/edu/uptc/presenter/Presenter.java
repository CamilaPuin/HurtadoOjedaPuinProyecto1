package co.edu.uptc.presenter;

import java.time.LocalDate;

import co.edu.uptc.model.System;

public class Presenter {
    private System system;

    public Presenter() {

        system = new System();
        // TODO metodos de orrdenamiento

    }
    // excepcion para logins???

    public void createRecepcionist(String name, String lastName, String email, String phone, String address,
            String id) {
        system.createRecepcionist(name, lastName, email, phone, address, id);
    }

    public void updateRecepcionist(String email, String phone, String address,
            String id, String newpassword, String passwordConfirm) {
        system.updateRecepcionist(email, phone, address, id, newpassword, passwordConfirm);
    }

    public void salesReport(LocalDate date) {
        system.salesReport(date);
    }

    public void logout() {
        system.logout();
    }

    public void availableSpaces() {
        system.availableSpaces();
    }

    public void registerVehicle(String plate, String type) {
        system.registerVehicle(plate, type);
    }

    public void exitVehicle(String plate) {
        system.exitVehicle(plate);
    }

    public boolean logIn(String addrees, String password, String userType) {
        return system.logIn(addrees, password, userType);

    }

    public String getFullName(String id) {
        return system.getFullName(id);
    }
    // login
    // meterle un while
    // el int option...

}