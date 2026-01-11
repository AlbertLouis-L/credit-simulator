package com.albertlouisl.creditsimulator;

import java.util.Scanner;
import com.albertlouisl.creditsimulator.controller.creditController;

public class App {
    public static void main(String[] args) {
        System.out.println("----------Credit Simulator Menu----------");
        System.out.println(">> show");
        System.out.println(">> exit");

        Scanner scanner = new Scanner(System.in);
        creditController CreditController = new creditController();

        while (true) {
            System.out.print("=> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()){
                continue;
            }

            switch (input.toLowerCase()) {
                case "show":
                    showMenu();
                    break;

                case "1":
                    CreditController.handleCreditSimulation(scanner);
                    break;

                case "exit":
                    System.out.println("Exit Complete. Terima kasih");
                    return;
            
                default:
                    System.out.println("Menu tidak diketahui. Silahkan ketik pilihan nomor di atas.");
            }
        }
    }

    private static void showMenu(){
            System.out.println("Available Menu:(1 - 4)");
            System.out.println("1. Calculate credit simulation");
            System.out.println("2. Save Current Sheet");
            System.out.println("3. Switch Sheet");
            System.out.println("4. exit");
    }

    // private static ArrayList<MonthlyInstallment> calculateCredit(String jenisKendaraan, String statusKendaraan, Integer tahunKendaraan, float jumlahPinjaman, Integer tenorPinjaman, float jumlahDP){
        
    //     double bunga = 0;
    //     ArrayList<MonthlyInstallment> monthly = new ArrayList<>();
    //     double pokokPinjaman = jumlahPinjaman - jumlahDP;

    //     if (jenisKendaraan.equalsIgnoreCase("motor")) {
    //         bunga = 9;
    //     }else if (jenisKendaraan.equalsIgnoreCase("mobil")) {
    //         bunga = 8;
    //     }
        

    //     for(int i = 1; i <= tenorPinjaman; i++){
    //         if (i%2 == 0) {
    //             bunga += 0.5;
    //         }else{
    //             bunga += 0.1;
    //         }

    //         double totalPinjaman = pokokPinjaman * ((100+bunga)/100);
    //         double monthlyInstallment = totalPinjaman / ((tenorPinjaman*12)- ((i-1)*12));
    //         monthly.add(new MonthlyInstallment(i,(float) bunga,(float) monthlyInstallment));
    //         pokokPinjaman = totalPinjaman - (monthlyInstallment*12);
    //     }

    //     return monthly;
    // }

    // private static void creditHandler(Scanner scanner){
    //     String jenisKendaraan = readJenisKendaraan(scanner);
    //     String statusKendaraan = readStatusKendaraan(scanner);
    //     int tahunKendaraan = readTahun(scanner, statusKendaraan);
    //     float jumlahPinjaman = readJumlahPinjaman(scanner);
    //     int tenor = readTenor(scanner);
    //     float jumlahDP = readDP(scanner, statusKendaraan, jumlahPinjaman);

    //     creditCalculator calculator = new creditCalculator();

    //     ArrayList<MonthlyInstallment> result =
    //             calculator.calculateCredit(jenisKendaraan, statusKendaraan, tahunKendaraan,
    //                     jumlahPinjaman, tenor, jumlahDP);

    //     System.out.println("=== Hasil Simulasi Kredit ===");

    //     Locale indonesia = Locale.forLanguageTag("id-ID");
    //     NumberFormat rupiah = NumberFormat.getCurrencyInstance(indonesia);

    //     for (MonthlyInstallment m : result) {
    //         String cicilan = rupiah.format(m.getMonthlyInstallment());
    //         String bungaFormatted = formatBunga(m.getBunga());

    //         System.out.printf(
    //             "tahun %d : %s/bln , Suku Bunga : %s%%%n",
    //             m.getTahun(),
    //             cicilan,
    //             bungaFormatted
    //         );
    //     }
    // }


    // private static String readJenisKendaraan(Scanner scanner) {
    //         while (true) {
    //             System.out.print("Jenis kendaraan (mobil/motor): ");
    //             String input = scanner.nextLine().trim().toLowerCase();

    //             if (input.equals("mobil") || input.equals("motor")) {
    //                 return input;
    //             }
    //             System.out.println("Input tidak valid.");
    //         }
    //     }

    //     private static String readStatusKendaraan(Scanner scanner) {
    //         while (true) {
    //         System.out.print("Status kendaraan (baru/bekas): ");
    //         String input = scanner.nextLine().trim().toLowerCase();

    //         if (input.equals("baru") || input.equals("bekas")) {
    //             return input;
    //         }
    //         System.out.println("Input tidak valid.");
    //         }
    //     }

    //     private static int readTahun(Scanner scanner, String statusKendaraan) {
    //         int currentYear = java.time.Year.now().getValue();

    //        while (true) {
    //             System.out.print("Tahun kendaraan (YYYY): ");
    //             String input = scanner.nextLine().trim();

    //             // must be exactly 4 digits
    //             if (!input.matches("\\d{4}")) {
    //                 System.out.println("Tahun harus terdiri dari 4 digit.");
    //                 continue;
    //             }

    //             int tahun = Integer.parseInt(input);

    //             if (statusKendaraan.equalsIgnoreCase("baru")) {
    //                 if (tahun == currentYear || tahun == currentYear - 1) {
    //                     return tahun;
    //                 }
    //                 System.out.println(
    //                     "Kendaraan baru hanya boleh tahun " +
    //                     currentYear + " atau " + (currentYear - 1)
    //                 );
    //             } else { // bekas
    //                 return tahun;
    //             }
    //         }
    //     }

    //     private static float readJumlahPinjaman(Scanner scanner) {
    //         while (true) {
    //             System.out.print("Jumlah pinjaman: ");
    //             try {
    //                 float amount = Float.parseFloat(scanner.nextLine());
    //                 if (amount > 0 && amount <= 1_000_000_000) {
    //                     return amount;
    //                 }
    //             } catch (NumberFormatException ignored) {}
    //             System.out.println("Jumlah pinjaman tidak valid.");
    //         }
    //     };

    //     private static int readTenor(Scanner scanner) {
    //         while (true) {
    //             System.out.print("Tenor (1-6 tahun): ");
    //             try {
    //                 int tenor = Integer.parseInt(scanner.nextLine());
    //                 if (tenor >= 1 && tenor <= 6) {
    //                     return tenor;
    //                 }
    //             } catch (NumberFormatException ignored) {}
    //             System.out.println("Tenor tidak valid.");
    //         }
    //     }

    //     private static float readDP(Scanner scanner, String status, float jumlahPinjaman) {
    //         float minPercentage = status.equals("bekas") ? 0.25f : 0.35f;
    //         float minDP = jumlahPinjaman * minPercentage;

    //         while (true) {
    //             System.out.printf("Jumlah DP (minimum %.0f): ", minDP);
    //             try {
    //                 float dp = Float.parseFloat(scanner.nextLine());
    //                 if (dp >= minDP) {
    //                     return dp;
    //                 }
    //             } catch (NumberFormatException ignored) {}
    //             System.out.println("DP tidak memenuhi syarat.");
    //         }
    //     }

    //     private static String formatBunga(float bunga) {
    //         if (bunga % 1 == 0) {
    //             return String.valueOf((int) bunga);
    //         }
    //         return String.valueOf(bunga).replace('.', ',');
    //     }








}
