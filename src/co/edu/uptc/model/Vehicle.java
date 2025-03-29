package co.edu.uptc.model;

public class Vehicle {
    private String plate;
    private String type;
    private String color;
    private LocalTime entryTime;
    private boolean isRegistered;

    public Vehicle(String plate, String type, LocalTime entryTime, boolean isRegistered) {
        super();
        this.plate = plate;
        this.type = type;
       this.entryTime= entryTime;
        this.isRegistered = isRegistered;
    }
    public String getPlate() {
        return plate;
    }
   
    
    public boolean isRegistered() {
        return isRegistered;
    }
    public void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }
}