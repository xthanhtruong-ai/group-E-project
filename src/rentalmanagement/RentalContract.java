
package rentalmanagement;

import java.time.LocalDate;

public class RentalContract {

    private int contractID;
    private Property property;
    private Tenant tenant;
    private LocalDate startDate;
    private LocalDate endDate;
    private double rentAmount;
    private boolean isActive;

    // Constructor: Create a rental contract and validate the dates and property
    public RentalContract(
            int contractID,
            Property property,
            Tenant tenant,
            LocalDate startDate,
            LocalDate endDate) {

        // Validate input before changing the property's availability
        if (property == null) {
            throw new IllegalArgumentException("Property cannot be null.");
        }

        if (tenant == null) {
            throw new IllegalArgumentException("Tenant cannot be null.");
        }

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null.");
        }

        if (!property.isAvailable()) {
            throw new IllegalStateException("Property is not available for rent.");
        }

        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date must be after start date.");
        }

        // Initialize contract attributes
        this.contractID = contractID;
        this.property = property;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentAmount = property.getRentalRate();
        this.isActive = true;

        // Mark the property as unavailable
        property.setAvailable(false);
    }

    // Terminate the rental contract
    public void Terminate() {
        if (isActive) {
            isActive = false;
            property.setAvailable(true);
        }
    }

    // Getter methods
    public int getContractID() {
        return contractID;
    }

    public Property getProperty() {
        return property;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public boolean isActive() {
        return isActive;
    }
}
