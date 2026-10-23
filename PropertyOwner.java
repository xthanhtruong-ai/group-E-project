package rentalmanagement;

import java.util.ArrayList;
import java.util.List;

public class PropertyOwner extends User {

    private List<Property> ownedProperties;

    public PropertyOwner(
            int userID,
            String firstName,
            String lastName,
            String email) {

        super(userID, firstName, lastName, email);
        this.ownedProperties = new ArrayList<>();
    }

    public Property CreateProperty(
            int propertyID,
            String description,
            double rentalRate) {

        Property property = new Property(
                propertyID,
                description,
                rentalRate
        );

        ownedProperties.add(property);
        return property;
    }

    public List<Property> GetProperties() {
        return new ArrayList<>(ownedProperties);
    }
}