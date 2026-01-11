package com.albertlouisl.creditsimulator.service;

import java.util.ArrayList;
import com.albertlouisl.creditsimulator.model.MonthlyInstallment;

public class CreditCalculator {

    public ArrayList<MonthlyInstallment> calculateCredit(
            String jenisKendaraan,
            String statusKendaraan,
            int tahunKendaraan,
            float jumlahPinjaman,
            int tenorPinjaman,
            float jumlahDP
    ) {

        double bunga = jenisKendaraan.equalsIgnoreCase("motor") ? 9 : 8;
        double pokokPinjaman = jumlahPinjaman - jumlahDP;

        ArrayList<MonthlyInstallment> result = new ArrayList<>();

        for (int i = 1; i <= tenorPinjaman; i++) {
            bunga += (i % 2 == 0) ? 0.5 : 0.1;

            double totalPinjaman = pokokPinjaman * (1 + bunga / 100);
            double monthlyInstallment =
                    totalPinjaman / ((tenorPinjaman * 12) - ((i - 1) * 12));

            result.add(new MonthlyInstallment(
                    i,
                    (float) bunga,
                    (float) monthlyInstallment
            ));

            pokokPinjaman = totalPinjaman - (monthlyInstallment * 12);
        }

        return result;
    }
}

