package com.albertlouisl.creditsimulator;

import java.util.Scanner;
import com.albertlouisl.creditsimulator.controller.CreditController;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CreditController CDController = new CreditController();
        
        if (args.length > 0) {
            CDController.loadSheetFromFile(args[0]);
        } else {
            // normal interactive menu
        }
        System.out.println("----------Credit Simulator Menu----------");
        System.out.println(">> show");
        System.out.println(">> exit");

        

        while (true) {
            System.out.print("=> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()){
                continue;
            }

            switch (input.toLowerCase()) {
                case "show":
                    System.out.println("----------Loading Existing Calculation----------\n");
                    CDController.handleLoadExistingCalculation();

                    showMenu();
                    break;

                case "1":
                    
                    CDController.handleCreditSimulation(scanner);
                    showMenu();
                    break;

                case "2":
                    CDController.saveCurrentSheet(scanner);
                    showMenu();
                    break;

                case "3":
                    CDController.switchSheet(scanner);
                    showMenu();
                    break;

                case "4":
                case "exit":
                    System.out.println("Exit Complete. Terima kasih");
                    return;

                // case "load":
                //     CDController.handleLoadExistingCalculation();
                //     break;
            
                default:
                    System.out.println("Menu tidak diketahui. Silahkan ketik pilihan di atas.");
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

}
