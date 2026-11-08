package rentalmanagement;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller: receives requests from the View and coordinates Model objects.
 * Business rules and validation remain in the Model classes.
 */
public class RentalController {

    private final PropertyOwner propertyOwner;
    private final Tenant tenant;

    public RentalController(PropertyOwner propertyOwner, Tenant tenant) {
        if (propertyOwner == null) {
            throw new IllegalArgumentException("Property owner cannot be null.");
        }
        if (tenant == null) {
            throw new IllegalArgumentException("Tenant cannot be null.");
        }

        this.propertyOwner = propertyOwner;
        this.tenant = tenant;
    }

    public Property createProperty(int propertyID, String description, double rentalRate) {
        return propertyOwner.CreateProperty(propertyID, description, rentalRate);
    }

    public RentalContract rentProperty(
            int contractID,
            int propertyID,
            LocalDate startDate,
            LocalDate endDate) {

        Property property = findProperty(propertyID);
        if (property == null) {
            throw new IllegalArgumentException("Property ID " + propertyID + " was not found.");
        }

        return tenant.RentProperty(contractID, property, startDate, endDate);
    }

    public void terminateContract(int contractID) {
        for (RentalContract contract : tenant.GetRentalContracts()) {
            if (contract.getContractID() == contractID) {
                if (!contract.isActive()) {
                    throw new IllegalStateException("This contract has already been terminated.");
                }
                contract.Terminate();
                return;
            }
        }

        throw new IllegalArgumentException("Contract ID " + contractID + " was not found.");
    }

    public List<Property> getProperties() {
        return propertyOwner.GetProperties();
    }

    public List<RentalContract> getRentalContracts() {
        return tenant.GetRentalContracts();
    }

    private Property findProperty(int propertyID) {
        for (Property property : propertyOwner.GetProperties()) {
            if (property.getPropertyID() == propertyID) {
                return property;
            }
        }
        return null;
    }
}
