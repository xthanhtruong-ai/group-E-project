package rentalmanagement;

public class Property {
    private int propertyID;
    private String description;
    private double rentalRate;
    private boolean isAvailable;

    // Constructor: Create a property and validate its rental rate
    public Property(int propertyID, String description, double rentalRate) {
        if (!Double.isFinite(rentalRate) || rentalRate <= 0) {
            throw new IllegalArgumentException(
                    "Rental rate must be greater than zero."
            );
        }

        this.propertyID = propertyID;
        this.description = description;
        this.rentalRate = rentalRate;
        this.isAvailable = true;
    }

    // Update the property description
    public void UpdateDescription(String description) {
        this.description = description;
    }

    // Update the rental rate only if the new rate is valid
    public void UpdateRentalRate(double rentalRate) {
        if (!Double.isFinite(rentalRate) || rentalRate <= 0) {
            throw new IllegalArgumentException(
                    "Rental rate must be greater than zero."
            );
        }

        this.rentalRate = rentalRate;
    }

    // Getter methods
    public int getPropertyID() {
        return propertyID;
    }

    public String getDescription() {
        return description;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Update availability when a rental contract is created or terminated
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}