package co.edu.uptc.model;

public class Ticket {
    private String vehiclePlate;
    private double cost;
    private double amountReceived;
    private double change;
    private int hours;

    public Ticket(String vehiclePlate, double cost, double amountReceived, double change, int hours) {
        this.vehiclePlate = vehiclePlate;
        this.cost = cost;
        this.amountReceived = amountReceived;
        this.change = change;
        this.hours = hours;
    }

    public static Ticket generateTicket(String plate, double cost, double amountReceived, int hours) {
        return new Ticket(plate, cost, amountReceived, calculateChange(amountReceived, cost), hours);
    }

    private static double calculateChange(double amountReceived, double cost) {
        return amountReceived - cost;
    }

    @Override
    public String toString() {
        return "Ticket [vehiclePlate=" + vehiclePlate + ", cost=" + cost + ", amountReceived=" + amountReceived
                + ", change=" + change + ", hours=" + hours + "]";
    }
    
}