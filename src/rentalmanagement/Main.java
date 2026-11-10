package rentalmanagement;


import rentalmanagement.controller.RentalController;
import rentalmanagement.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        // Create the Model objects used by this application.
        PropertyOwner propertyOwner = new PropertyOwner(
                1, "Alex", "Owner", "alex.owner@example.com");

        Tenant tenant = new Tenant(
                2, "Jamie", "Tenant", "jamie.tenant@example.com");

        // Connect Model, Controller, and View.
        RentalController controller = new RentalController(propertyOwner, tenant);
        ConsoleView view = new ConsoleView(controller);

        // Start the application.
        view.start();
    }
}
