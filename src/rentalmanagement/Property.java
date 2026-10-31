package rentalmanagement;

public class Property {
    private int propertyID;
    private String description;
    private double rentalRate;
    private boolean isAvailable;

    public Property(int propertyID, String description, double rentalRate) {

        // Week 3: Validate PropertyID
        if (propertyID <= 0) {
            throw new IllegalArgumentException(
                "Property ID must be a positive integer."
            );
        }

        // Week 3: Validate Description
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Description cannot be empty."
            );
        }

        // Validate RentalRate
        if (!Double.isFinite(rentalRate) || rentalRate <= 0) {
            throw new IllegalArgumentException(
                "Rental rate must be greater than zero."
            );
        }

        this.propertyID = propertyID;
        this.description = description.trim();
        this.rentalRate = rentalRate;
        this.isAvailable = true;
    }

    public void UpdateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Description cannot be empty."
            );
        }

        this.description = description.trim();
    }

    public void UpdateRentalRate(double rentalRate) {
        if (!Double.isFinite(rentalRate) || rentalRate <= 0) {
            throw new IllegalArgumentException(
                "Rental rate must be greater than zero."
            );
        }

        this.rentalRate = rentalRate;
    }

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

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}