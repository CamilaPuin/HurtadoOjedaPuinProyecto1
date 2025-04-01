package co.edu.uptc.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class System {
    private Admin admin;
    private Recepcionist recepcionist;
    public System() {
        admin = new Admin("admin", "admin", "admin@admin.com", "123456789", "Calle de la casa, 1", "admin");
        admin.registerParking("Parking UPTC", "UPTC", "parkinguptc", 10, 10, LocalTime.now(), new ArrayList<>());
        recepcionist = new Recepcionist("recepcionist", "recepcionist", "recepcionist@recepcionist.com", "123456789",
                "Calle de la casa, 1", "recepcionist");
    }
}
