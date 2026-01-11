package com.albertlouisl.creditsimulator.controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import com.albertlouisl.creditsimulator.model.LoanData;

import com.albertlouisl.creditsimulator.model.MonthlyInstallment;
import com.albertlouisl.creditsimulator.service.CreditCalculator;
import com.albertlouisl.creditsimulator.service.SheetService;
import com.albertlouisl.creditsimulator.view.ViewMenu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public void loadSheetFromFile(String filename) {
        if (filename.endsWith(".txt")) {
            filename = filename.substring(0, filename.length() - 4);
        }

        ArrayList<MonthlyInstallment> sheet = sheetService.loadSheet(filename);
        if (sheet != null) {
            currentSheet = sheet;
            view.displaySheet(currentSheet);
        } else {
            System.out.println("File " + filename + " tidak ditemukan.");
        }
    }

   public void handleLoadExistingCalculation() {
        String apiUrl = "https://run.mocky.io/v3/9108b1da-beec-409e-ae14-e212003666c";
        try {
            String json = ApiClient.fetchJson(apiUrl);
            LoanData loanData = parseJson(json);

            ArrayList<MonthlyInstallment> result =
                    calculator.calculateCredit(
                            loanData.getVehicleType(),
                            loanData.getVehicleCondition(),
                            loanData.getVehicleYear(),
                            (float) loanData.getTotalLoanAmount(),
                            loanData.getLoanTenure(),
                            (float) loanData.getDownPayment()
                    );

            currentSheet = result;
            view.showResult(result);
            System.out.println("\n");

        } catch (RuntimeException e) {
            System.out.println("Gagal memuat data dari API: " + e.getMessage());
            System.out.println("\n");
        }
    }

    public LoanData parseJson(String json) {
        LoanData loan = new LoanData();
        json = json.replaceAll("[\\{\\}\"]", ""); // crude parsing
        String[] parts = json.split(",");
        for (String part : parts) {
            String[] kv = part.split(":");
            if (kv.length < 2) continue;
            String key = kv[0].trim();
            String value = kv[1].trim();
            switch (key) {
                case "vehicleType":
                    loan.setVehicleType(value);
                    break;
                case "vehicleCondition":
                    loan.setVehicleCondition(value);
                    break;
                case "vehicleYear":
                    loan.setVehicleYear(Integer.parseInt(value));
                    break;
                case "totalLoanAmount":
                    loan.setTotalLoanAmount(Double.parseDouble(value));
                    break;
                case "loanTenure":
                    loan.setLoanTenure(Integer.parseInt(value));
                    break;
                case "downPayment":
                    loan.setDownPayment(Double.parseDouble(value));
                    break;
            }
        }
        return loan;
    }

    public static class ApiClient {

        public static String fetchJson(String urlString) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // ✅ CHANGE: no SSL hacks
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("HTTP Error: " + conn.getResponseCode());
                }

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();

            } catch (Exception e) {
                throw new RuntimeException("Error fetching API: " + e.getMessage());
            }
        }
    }


}
