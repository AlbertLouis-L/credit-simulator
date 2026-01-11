package com.albertlouisl.creditsimulator.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.albertlouisl.creditsimulator.model.MonthlyInstallment;
import com.albertlouisl.creditsimulator.service.CreditCalculator;
import com.albertlouisl.creditsimulator.view.ViewMenu;

public class CreditController {

    private final CreditCalculator calculator = new CreditCalculator();
    private final ViewMenu view = new ViewMenu();

    public void handleCreditSimulation(Scanner scanner) {

        String jenisKendaraan = view.readJenisKendaraan(scanner);
        String statusKendaraan = view.readStatusKendaraan(scanner);
        int tahunKendaraan = view.readTahun(scanner, statusKendaraan);
        float jumlahPinjaman = view.readJumlahPinjaman(scanner);
        int tenor = view.readTenor(scanner);
        float jumlahDP = view.readDP(scanner, statusKendaraan, jumlahPinjaman);

        ArrayList<MonthlyInstallment> result =
                calculator.calculateCredit(
                        jenisKendaraan,
                        statusKendaraan,
                        tahunKendaraan,
                        jumlahPinjaman,
                        tenor,
                        jumlahDP
                );

        view.showResult(result);
    }
}
