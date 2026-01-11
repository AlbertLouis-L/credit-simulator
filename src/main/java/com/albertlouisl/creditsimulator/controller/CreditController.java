package com.albertlouisl.creditsimulator.controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import com.albertlouisl.creditsimulator.model.MonthlyInstallment;
import com.albertlouisl.creditsimulator.service.CreditCalculator;
import com.albertlouisl.creditsimulator.service.SheetService;
import com.albertlouisl.creditsimulator.view.ViewMenu;

public class CreditController {

    private final CreditCalculator calculator = new CreditCalculator();
    private final ViewMenu view = new ViewMenu();
    private final SheetService sheetService = new SheetService();
    private ArrayList<MonthlyInstallment> currentSheet = new ArrayList<>();

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

        currentSheet = result;

        view.showResult(result);
        System.out.println("\n");
    }

    public void saveCurrentSheet(Scanner scanner) {
        if (currentSheet.isEmpty()) {
            System.out.println("Tidak ada sheet yang dapat disimpan. Jalankan simulasi kredit terlebih dahulu.");
            return;
        }

        System.out.print("Masukkan nama sheet untuk disimpan: ");
        String sheetName = scanner.nextLine().trim();
        if (!sheetName.isEmpty()) {
            sheetService.saveSheet(sheetName, currentSheet);
        } else {
            System.out.println("Nama sheet tidak boleh kosong.");
        }
    }

    public void switchSheet(Scanner scanner) {
        List<String> sheets = sheetService.listSheets();
        if (sheets.isEmpty()) {
            System.out.println("Tidak ada sheet yang tersedia.");
            return;
        }

        System.out.println("Available sheets:");
        for (int i = 0; i < sheets.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, sheets.get(i));
        }

        while (true) {
            System.out.print("Pilih nomor sheet untuk dibuka: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= sheets.size()) {
                    currentSheet = sheetService.loadSheet(sheets.get(choice - 1));
                    System.out.println("Sheet " + sheets.get(choice - 1) + " berhasil dibuka.");
                    // optionally, show loaded sheet
                    view.displaySheet(currentSheet);
                    return;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Input tidak valid.");
        }
    }

}
