package co.edu.uptc.presenter;

import co.edu.uptc.model.SystemParking;
import co.edu.uptc.model.Ticket;

import java.time.LocalDate;

public class Presenter {
    private SystemParking system;

    public Presenter() {

        system = new SystemParking();
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

    public String availableSpaces() {
        return system.availableSpaces();
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
   
    
    public double costTikect(String plate, double amountReceived){
        return system.costTikect(plate, amountReceived);
    }

    public double calculteChange(String plate, double amountReceived){
        return system.calculteChange(plate, amountReceived);
    }
    
    public int hoursVehicle(String plate){
        return system.hoursVehicle(plate);
    }

   
    // meterle un while
    // el int option...

}