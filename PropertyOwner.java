package rentalmanagement;

import java.util.ArrayList;
import java.util.List;

public class PropertyOwner extends User {
    private List<Property> properties;

    public PropertyOwner(String userId, String fullName, String email, String phone) {
        super(userId, fullName, email, phone);
        this.properties = new ArrayList<>();
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        if (property != null && !properties.contains(property)) {
            properties.add(property);
        }
    }
}