package co.edu.uptc.presenter;

import java.time.LocalDate;

import co.edu.uptc.model.System;
import co.edu.uptc.view.View;

public class Presenter {
    private System system;
    private View view;

    public Presenter() {

        system = new System();
        // TODO metodos de orrdenamiento

    }
    // excepcion para logins???

    public String createRecepcionist(String name, String lastName, String email, String phone, String address,
            String id) {
        return system.createRecepcionist(name, lastName, email, phone, address, id);
    }

    public String updateRecepcionist(String email, String phone, String address,
            String id, String newpassword, String passwordConfirm) {
        return system.updateRecepcionist(email, phone, address, id, newpassword, passwordConfirm);
    }

    public String salesReport(LocalDate date) {
       return system.salesReport(date);
    }

    public String logout() {
       return system.logout();
    }

    public String availableSpaces() {
       return system.availableSpaces();
    }

    public String registerVehicle(String plate, String type) {
       return system.registerVehicle(plate, type);
    }

    public String exitVehicle(String plate) {
       return system.exitVehicle(plate);
    }

    public boolean logIn(String addrees, String password, String userType) {
        return system.logIn(addrees, password, userType);

    }

    // login
    // meterle un while
    // el int option...

}