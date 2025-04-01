package co.edu.uptc.presenter;

import java.time.LocalDate;

import co.edu.uptc.model.Recepcionist;
import co.edu.uptc.model.System;
import co.edu.uptc.view.View;

public class Presenter {
    private View view;
    private System system;

    public Presenter() {
        view = new View();
        system = new System();
        // TODO metodos de orrdenamiento

    }
    //excepcion para logins???

    public void createRecepcionist(String name, String lastName, String email, String phone, String address,
    String id, String password){
        system.createRecepcionist(name, lastName, email, phone, address, id, password);
    }
    public void updateRecepcionist(String name, String lastName, String email, String phone, String address,
            String id, String password){
        system.updateRecepcionist(name, lastName, email, phone, address, id, password);
    }
    public void salesReport(LocalDate date){
        system.salesReport(date);
    }
    public void logout(){
        system.logout();
    }
    public void availableSpaces(){
        system.availableSpaces();
    }
    public void registerVehicle(String plate, String type){
        system.registerVehicle(plate, type);
    }
    public void exitVehicle(String plate){
        system.exitVehicle(plate);
    }       
        // login
        // meterle un while
        // el int option...
        
}