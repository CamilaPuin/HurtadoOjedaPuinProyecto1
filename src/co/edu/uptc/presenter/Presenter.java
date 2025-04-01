package co.edu.uptc.presenter;

import co.edu.uptc.model.System;
import co.edu.uptc.view.View;

public class Presenter {
    private View view;
    private System system;

    public Presenter() {
        view = new View();
        system = new System();
        // TODO metodos de orrdenamiento
        menu(0);
    }

    private void menu(int option) {
        // login
        // meterle un while
        // el int option...
        if (system.getCurrentAdmin() != null) {
            switch (option) {
                case 0:
                    system.adminregisterparking();
                    break;
                case 1:
                    system.createRecepcionist();
                    break;
                case 2:
                    system.updateRecepcionist();
                    break;
                case 3:
                    system.salesReport();
                    break;
                case 4:
                    system.logout();
                    break;
                default:
                    // error????
                    break;
            }
        } else if (system.getCurrentRecepcionist() != null) {
            switch (option) {
                case 0:
                    system.availableSpaces();
                    break;
                case 1:
                    system.registerVehicle();
                    break;
                case 2:
                    system.exitVehicle();
                    break;
                case 3:
                    system.logout();
                    break;
            }
        }
    }
}