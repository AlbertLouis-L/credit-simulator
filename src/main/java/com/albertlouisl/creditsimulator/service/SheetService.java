package com.albertlouisl.creditsimulator.service;

import com.albertlouisl.creditsimulator.model.MonthlyInstallment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SheetService {

    private final String folderPath = new File("").getAbsolutePath() + "/sheets"; // folder to store files

    public SheetService() {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir(); // create folder if it doesn't exist
        }
    }

    // Save a sheet to a text file
    public void saveSheet(String sheetName, ArrayList<MonthlyInstallment> sheet) {
        File file = new File(folderPath + "/" + sheetName + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (MonthlyInstallment m : sheet) {
            
                writer.write(m.getTahun() + "," + m.getBunga() + "," + m.getMonthlyInstallment());
                writer.newLine();
            }
            System.out.println("Sheet saved successfully as " + sheetName + ".txt");
        } catch (IOException e) {
            System.out.println("Error saving sheet: " + e.getMessage());
        }
    }

    // Load a sheet from a text file
    public ArrayList<MonthlyInstallment> loadSheet(String sheetName) {
        File file = new File(folderPath + "/" + sheetName + ".txt");
        ArrayList<MonthlyInstallment> sheet = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("Sheet " + sheetName + " does not exist.");
            return sheet;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int tahun = Integer.parseInt(parts[0]);
                    float bunga = Float.parseFloat(parts[1]);
                    float monthlyInstallment = Float.parseFloat(parts[2]);
                    sheet.add(new MonthlyInstallment(tahun, bunga, monthlyInstallment));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading sheet: " + e.getMessage());
        }

        return sheet;
    }

    // List all available sheets
    public List<String> listSheets() {
        File folder = new File(folderPath);
        String[] files = folder.list((dir, name) -> name.endsWith(".txt"));
        List<String> sheetNames = new ArrayList<>();
        if (files != null) {
            for (String f : files) {
                sheetNames.add(f.replace(".txt", ""));
            }
        }
        return sheetNames;
    }
}
