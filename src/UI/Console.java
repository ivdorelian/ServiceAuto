package UI;

import Domain.StandReportViewModel;
import Service.CarService;

import java.util.List;
import java.util.Scanner;

public class Console {

    private CarService service;
    private Scanner in = new Scanner(System.in);

    public Console(CarService service) {
        this.service = service;
    }

    private void showMenu() {
        System.out.println("1. Add car to repair shop.");
        System.out.println("2. Remove car from repair shop.");
        System.out.println("3. Show stands report.");
        System.out.println("0. Exit.");
        System.out.print("Your option: ");
    }

    private void handleEntry() {
        System.out.print("Entry id: ");
        int id = in.nextInt();
        System.out.print("Stand number: ");
        int standNumber = in.nextInt();
        System.out.print("License plate number: ");
        in.nextLine();
        String licensePlateNumber = in.nextLine();
        System.out.print("Number of days: ");
        int days = in.nextInt();

        try {
            service.enterRepairShop(id, standNumber, licensePlateNumber, days);
            System.out.println("Car entered repair shop successfully!");
        } catch (RuntimeException rex) {
            System.out.println("We have errors:");
            System.out.println(rex.getMessage());
        }
    }

    private void handleExit() {
        System.out.print("Stand number: ");
        int standNumber = in.nextInt();
        System.out.print("Report: ");
        in.nextLine();
        String report = in.nextLine();
        System.out.print("Billed price: ");
        double price = in.nextDouble();

        try {
            service.leaveRepairShop(standNumber, report, price);
            System.out.println("Car left repair shop successfully!");
        } catch (RuntimeException rex) {
            System.out.println("We have errors:");
            System.out.println(rex.getMessage());
        }
    }

    private void handleReport() {

        List<StandReportViewModel> standReports = service.getStandsReport();
        for (StandReportViewModel standReport : standReports) {
            System.out.println(String.format("%d %f", standReport.getStandNumber(), standReport.getPriceAverage()));
        }
    }

    public void run() {

        while (true) {
            showMenu();

            int option = in.nextInt();
            if (option == 1) {
                handleEntry();
            } else if (option == 2) {
                handleExit();
            } else if (option == 3) {
                handleReport();
            } else if (option == 0) {
                break;
            } else {
                System.out.println("Invalid option!");
            }
        }
    }
}
