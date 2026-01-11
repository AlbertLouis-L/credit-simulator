package com.albertlouisl.creditsimulator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.text.NumberFormat;

import com.albertlouisl.creditsimulator.model.MonthlyInstallment;

public class ViewMenu {

    public void showResult(ArrayList<MonthlyInstallment> result) {
        System.out.println("=== Hasil Simulasi Kredit ===");

        Locale indonesia = Locale.forLanguageTag("id-ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(indonesia);

        for (MonthlyInstallment m : result) {
            String cicilan = rupiah.format(m.getMonthlyInstallment());
            String bunga = formatBunga(m.getBunga());

            System.out.printf(
                "tahun %d : %s/bln , Suku Bunga : %s%%%n",
                m.getTahun(),
                cicilan,
                bunga
            );
        }
    }

    private String formatBunga(float bunga) {
        if (bunga % 1 == 0) return String.valueOf((int) bunga);
        return String.valueOf(bunga).replace('.', ',');
    }

    public String readJenisKendaraan(Scanner scanner) {
            while (true) {
                System.out.print("Jenis kendaraan (mobil/motor): ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("mobil") || input.equals("motor")) {
                    return input;
                }
                System.out.println("Input tidak valid.");
            }
    }

    public String readStatusKendaraan(Scanner scanner) {
        while (true) {
        System.out.print("Status kendaraan (baru/bekas): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("baru") || input.equals("bekas")) {
            return input;
        }
        System.out.println("Input tidak valid.");
        }
    }

    public int readTahun(Scanner scanner, String statusKendaraan) {
        int currentYear = java.time.Year.now().getValue();

        while (true) {
            System.out.print("Tahun kendaraan (YYYY): ");
            String input = scanner.nextLine().trim();

            // must be exactly 4 digits
            if (!input.matches("\\d{4}")) {
                System.out.println("Tahun harus terdiri dari 4 digit.");
                continue;
            }

            int tahun = Integer.parseInt(input);

            if (statusKendaraan.equalsIgnoreCase("baru")) {
                if (tahun == currentYear || tahun == currentYear - 1) {
                    return tahun;
                }
                System.out.println(
                    "Kendaraan baru hanya boleh tahun " +
                    currentYear + " atau " + (currentYear - 1)
                );
            } else { // bekas
                return tahun;
            }
        }
    }

    public float readJumlahPinjaman(Scanner scanner) {
        while (true) {
            System.out.print("Jumlah pinjaman: ");
            try {
                float amount = Float.parseFloat(scanner.nextLine());
                if (amount > 0 && amount <= 1_000_000_000) {
                    return amount;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Jumlah pinjaman tidak valid.");
        }
    };

    public int readTenor(Scanner scanner) {
        while (true) {
            System.out.print("Tenor (1-6 tahun): ");
            try {
                int tenor = Integer.parseInt(scanner.nextLine());
                if (tenor >= 1 && tenor <= 6) {
                    return tenor;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Tenor tidak valid.");
        }
    }

    public float readDP(Scanner scanner, String status, float jumlahPinjaman) {
        float minPercentage = status.equals("bekas") ? 0.25f : 0.35f;
        float minDP = jumlahPinjaman * minPercentage;

        while (true) {
            System.out.printf("Jumlah DP (minimum %.0f): ", minDP);
            try {
                float dp = Float.parseFloat(scanner.nextLine());
                if (dp >= minDP) {
                    return dp;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("DP tidak memenuhi syarat.");
        }
    }

    public void displaySheet(List<MonthlyInstallment> sheet) {
        System.out.println("=== Loaded Sheet ===");
        Locale indonesia = Locale.forLanguageTag("id-ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(indonesia);

        for (MonthlyInstallment m : sheet) {
            String cicilan = rupiah.format(m.getMonthlyInstallment());
            String bunga = formatBunga(m.getBunga());

            System.out.printf(
                "tahun %d : %s/bln , Suku Bunga : %s%%%n",
                m.getTahun(),
                cicilan,
                bunga
            );
        }
    }


    
}
