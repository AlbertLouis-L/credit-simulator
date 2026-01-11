package com.albertlouisl.creditsimulator.model;

public class MonthlyInstallment {
    private final int tahun;
    private final float bunga;
    private final float monthlyInstallment;

    public MonthlyInstallment(int tahun, float bunga, float monthlyInstallment) {
        this.tahun = tahun;
        this.bunga = bunga;
        this.monthlyInstallment = monthlyInstallment;
    }

    //Getters
    public int getTahun() { return tahun; }
    public float getBunga() { return bunga; }
    public float getMonthlyInstallment() { return monthlyInstallment; }

}
