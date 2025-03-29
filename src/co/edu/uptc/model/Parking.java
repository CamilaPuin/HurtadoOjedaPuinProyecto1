package co.edu.uptc.model;

public class Parking {
    private String name;
    private String address;
    private String id;
    private int capacity;
   private LocalTime openingHour;
   private ArrayList<Vehicle> vehicles;
   private ArrayList<Recepcionist> recepcionists;

    public Parking(String name, String address, int capacity, LocalTime openingHour, ArrayList<Vehicle> vehicles, ArrayList<Recepcionist> recepcionists) {
        super();
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.openingHour = openingHour;
        this.vehicles = vehicles;
        this.recepcionists = recepcionists;
    }

    public int updateAvailability(){
        
    }
}