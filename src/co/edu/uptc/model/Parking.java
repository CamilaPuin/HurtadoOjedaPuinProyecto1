package co.edu.uptc.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Parking {
    private String name;
    private String address;
    private String id;
    private int carscapacity;
    private int motorbikescapacity;
    private LocalTime openingHour;
    private ArrayList<Vehicle> motorbikes;
    private ArrayList<Vehicle> cars;
    private ArrayList<Recepcionist> recepcionists;

    public Parking(String name, String address, String id, int carscapacity, int motorbikescapacity,
            LocalTime openingHour, ArrayList<Vehicle> motorbikes, ArrayList<Vehicle> cars,
            ArrayList<Recepcionist> recepcionists) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.carscapacity = carscapacity;
        this.motorbikescapacity = motorbikescapacity;
        this.openingHour = openingHour;
        this.motorbikes = motorbikes;
        this.cars = cars;
        this.recepcionists = recepcionists;
    }

    public Parking(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCarscapacity() {
        return carscapacity;
    }

    public void setCarscapacity(int carscapacity) {
        this.carscapacity = carscapacity;
    }

    public int getMotorbikescapacity() {
        return motorbikescapacity;
    }

    public void setMotorbikescapacity(int motorbikescapacity) {
        this.motorbikescapacity = motorbikescapacity;
    }

    public LocalTime getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(LocalTime openingHour) {
        this.openingHour = openingHour;
    }

    public ArrayList<Vehicle> getMotorbikes() {
        return motorbikes;
    }

    public void setMotorbikes(ArrayList<Vehicle> motorbikes) {
        this.motorbikes = motorbikes;
    }

    public ArrayList<Vehicle> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Vehicle> cars) {
        this.cars = cars;
    }

    public ArrayList<Recepcionist> getRecepcionists() {
        return recepcionists;
    }

    public void setRecepcionists(ArrayList<Recepcionist> recepcionists) {
        this.recepcionists = recepcionists;
    }

    public String updateAvailability() {
        int availableCars = carscapacity - cars.size();
        int availableMotorbikes = motorbikescapacity - motorbikes.size();
        int totalAvailable = availableCars + availableMotorbikes;
        return String.format(
                "Hay %d espacios disponibles%nMoto: %d espacios disponibles%nCarro: %d espacios disponibles",
                totalAvailable, availableMotorbikes, availableCars);
    }

    public void registerVehicle(String plate, String type, LocalTime entryTime) {
<<<<<<< HEAD
        switch (type) {
            case "Carro" -> {
                if (carscapacity > cars.size()) {
                    Vehicle vehicle = new Vehicle(plate, type, entryTime);
                    cars.add(vehicle);
                    cars.sort(Comparator.comparing(Vehicle::getPlate));
                } else
                    System.out.println("No hay espacio disponible para carros.");
            }
            case "Moto" -> {
                if (motorbikescapacity > motorbikes.size()) {
                    Vehicle vehicle = new Vehicle(plate, type, entryTime);
                    motorbikes.add(vehicle);
                    motorbikes.sort(Comparator.comparing(Vehicle::getPlate));
                } else
                    System.out.println("No hay espacio disponible para motos.");
            }
            default -> System.out.println("Tipo de vehículo no reconocido.");
=======
        if (type.equals("Carro")) {
            if (carscapacity > cars.size()) {
                cars.add(new Vehicle(plate, type, entryTime));
                cars.sort(Comparator.comparing(Vehicle::getPlate));
            }
        }
        if (type.equals("Moto")) {
            if (motorbikescapacity > motorbikes.size()) {
                motorbikes.add(new Vehicle(plate, type, entryTime));
                motorbikes.sort(Comparator.comparing(Vehicle::getPlate));
            }
>>>>>>> c99c69e040530b97f1933f840f99c706dc22bccf
        }
    }

    public Vehicle deleteVehicle(String plate) {
        cars.sort(Comparator.comparing(Vehicle::getPlate));
        motorbikes.sort(Comparator.comparing(Vehicle::getPlate));
        int index = Collections.binarySearch(cars, new Vehicle(plate), Comparator.comparing(Vehicle::getPlate));
        if (index >= 0)
            return cars.remove(index);

        index = Collections.binarySearch(motorbikes, new Vehicle(plate), Comparator.comparing(Vehicle::getPlate));
        if (index >= 0)
            return motorbikes.remove(index);
        System.out.println("No se encontró el vehículo con la placa: " + plate);
        return null;
    }

    public double calculateCost(String plate) {
        Vehicle vehicle = getVehicle(plate);
        double costPerHour;
        if (vehicle != null)
            costPerHour = "car".equals(vehicle.getType()) ? 2000 : 1000;
<<<<<<< HEAD
        else
=======
        } else {
>>>>>>> c99c69e040530b97f1933f840f99c706dc22bccf
            return -1;
        return costPerHour * getPassedTime(vehicle);
    }

    public static int getPassedTime(Vehicle vehicle) {
        if (vehicle == null)
            return -1;
        Duration passedTime = Duration.between(vehicle.getEntryTime(), LocalTime.now());
        return (int) (passedTime.toMinutes() + 59) / 60;
    }

    public Vehicle getVehicle(String plate) {
        int index = Collections.binarySearch(cars, new Vehicle(plate), Comparator.comparing(Vehicle::getPlate));
        Vehicle vehicle = null;
        if (index >= 0) {
            vehicle = cars.get(index);
        } else {
            index = Collections.binarySearch(motorbikes, new Vehicle(plate), Comparator.comparing(Vehicle::getPlate));
            if (index >= 0) {
                vehicle = motorbikes.get(index);
            }
        }
        return vehicle;
    }

    public boolean foundedVehicle(String plate) {
        return Collections.binarySearch(cars, new Vehicle(plate), Comparator.comparing(Vehicle::getPlate)) >= 0;
    }

}