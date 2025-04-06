package co.edu.uptc.model;

public class Ticket {
    private String vehiclePlate;
    private double cost;
    private double amountReceived;
    private double change;
    private int hours;

    public Ticket(String vehiclePlate, double cost, double amountReceived, int hours) {
        this.vehiclePlate = vehiclePlate;
        this.cost = cost;
        this.amountReceived = amountReceived;
        this.change = calculateChange(amountReceived, cost);
        this.hours = hours;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(double amountReceived) {
        this.amountReceived = amountReceived;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


    public double calculateChange(double amountReceived, double cost) {
        return amountReceived - cost;
    }

    @Override
    public String toString() {
        return "Ticket [vehiclePlate=" + vehiclePlate + ", cost=" + cost + ", amountReceived=" + amountReceived
                + ", change=" + change + ", hours=" + hours + "]";
    }

}