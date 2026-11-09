package rentalmanagement.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import rentalmanagement.Property;
import rentalmanagement.RentalContract;
import rentalmanagement.controller.RentalController;

/** View: displays the menu, reads user input, and displays results. */
public class ConsoleView {

    private final RentalController controller;
    private final Scanner scanner;

    public ConsoleView(RentalController controller) {
        if (controller == null) {
            throw new IllegalArgumentException("Controller cannot be null.");
        }
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1:
                        createProperty();
                        break;
                    case 2:
                        rentProperty();
                        break;
                    case 3:
                        terminateContract();
                        break;
                    case 4:
                        running = false;
                        System.out.println("Thank you for using Rental Management Application.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose from 1 to 4.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private void displayMenu() {
        System.out.println("====================================");
        System.out.println("       RENTAL MANAGEMENT SYSTEM     ");
        System.out.println("====================================");
        System.out.println("1. Create property");
        System.out.println("2. Rent property");
        System.out.println("3. Terminate contract");
        System.out.println("4. Exit");
        System.out.println("------------------------------------");
    }

    private void createProperty() {
        System.out.println("\n--- CREATE PROPERTY ---");
        int propertyID = readInt("Property ID: ");
        System.out.print("Description: ");
        String description = scanner.nextLine();
        double rentalRate = readDouble("Rental rate: ");

        Property property = controller.createProperty(propertyID, description, rentalRate);
        System.out.println("Property created successfully.");
        displayProperty(property);
    }

    private void rentProperty() {
        System.out.println("\n--- RENT PROPERTY ---");
        List<Property> properties = controller.getProperties();
        if (properties.isEmpty()) {
            System.out.println("There are no properties. Please create a property first.");
            return;
        }

        displayProperties(properties);
        int propertyID = readInt("Enter the Property ID to rent: ");
        int contractID = readInt("Contract ID: ");
        LocalDate startDate = readDate("Start date (yyyy-MM-dd): ");
        LocalDate endDate = readDate("End date (yyyy-MM-dd): ");

        RentalContract contract = controller.rentProperty(
                contractID, propertyID, startDate, endDate);

        System.out.println("Property rented successfully.");
        System.out.println("Contract ID: " + contract.getContractID());
        System.out.println("Property ID: " + contract.getProperty().getPropertyID());
        System.out.println("Rent amount: " + contract.getRentAmount());
        System.out.println("Property availability: "
                + contract.getProperty().isAvailable());
    }

    private void terminateContract() {
        System.out.println("\n--- TERMINATE CONTRACT ---");
        List<RentalContract> contracts = controller.getRentalContracts();
        if (contracts.isEmpty()) {
            System.out.println("There are no rental contracts.");
            return;
        }

        displayContracts(contracts);
        int contractID = readInt("Enter the Contract ID to terminate: ");
        controller.terminateContract(contractID);
        System.out.println("Contract terminated successfully.");

        for (RentalContract contract : controller.getRentalContracts()) {
            if (contract.getContractID() == contractID) {
                System.out.println("Property availability: "
                        + contract.getProperty().isAvailable());
                break;
            }
        }
    }

    private void displayProperties(List<Property> properties) {
        System.out.println("\n--- PROPERTY LIST ---");
        for (Property property : properties) {
            displayProperty(property);
        }
    }

    private void displayProperty(Property property) {
        System.out.println("Property ID: " + property.getPropertyID());
        System.out.println("Description: " + property.getDescription());
        System.out.println("Rental rate: " + property.getRentalRate());
        System.out.println("Available: " + property.isAvailable());
        System.out.println("------------------------------------");
    }

    private void displayContracts(List<RentalContract> contracts) {
        System.out.println("\n--- RENTAL CONTRACT LIST ---");
        for (RentalContract contract : contracts) {
            System.out.println("Contract ID: " + contract.getContractID()
                    + " | Property ID: " + contract.getProperty().getPropertyID()
                    + " | Active: " + contract.isActive());
        }
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double readDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private LocalDate readDate(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please use yyyy-MM-dd, for example 2026-11-07.");
            }
        }
    }
}