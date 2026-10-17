
package rentalmanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tenant extends User {

    // Additional attribute: rental contracts belonging to this tenant
    private List<RentalContract> rentalContracts;

    // Constructor: initialize inherited attributes and an empty contract list
    public Tenant(int userID, String firstName, String lastName, String email) {
        super(userID, firstName, lastName, email);
        this.rentalContracts = new ArrayList<>();
    }

    // RentProperty(): create a contract, then add it to the list
    public RentalContract RentProperty(
            int contractID,
            Property property,
            LocalDate startDate,
            LocalDate endDate) {

        RentalContract contract = new RentalContract(
                contractID,
                property,
                this,
                startDate,
                endDate
        );

        // Add only after the contract has been created successfully
        rentalContracts.add(contract);

        return contract;
    }

    // GetRentalContracts(): return all contracts, including terminated ones
    public List<RentalContract> GetRentalContracts() {
        return new ArrayList<>(rentalContracts);
    }
}
