package com.albertlouisl.creditsimulator.model;

public class LoanData {
    private String vehicleType;
    private String vehicleCondition;
    private int vehicleYear;
    private double totalLoanAmount;
    private int loanTenure;
    private double downPayment;

    // Getters
    public String getVehicleType() { return vehicleType; }
    public String getVehicleCondition() { return vehicleCondition; }
    public int getVehicleYear() { return vehicleYear; }
    public double getTotalLoanAmount() { return totalLoanAmount; }
    public int getLoanTenure() { return loanTenure; }
    public double getDownPayment() { return downPayment; }

    //Setters
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public void setVehicleCondition(String vehicleCondition) { this.vehicleCondition = vehicleCondition; }
    public void setVehicleYear(int year) { this.vehicleYear = year; }
    public void setTotalLoanAmount(double amount) { this.totalLoanAmount = amount; }
    public void setLoanTenure(int tenure) { this.loanTenure = tenure; }
    public void setDownPayment(double dp) { this.downPayment = dp; }

}
