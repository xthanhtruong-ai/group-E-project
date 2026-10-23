
package rentalmanagement;

public class User {

    // Attributes: information shared by property owners and tenants
    private int userID;
    private String firstName;
    private String lastName;
    private String email;

    // Constructor: initialize all attributes
    public User(int userID, String firstName, String lastName, String email) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // GetFullName(): return first name and last name separated by a space
    public String GetFullName() {
        return firstName + " " + lastName;
    }

    // UpdateProfile(): update profile information but keep userID unchanged
    public void UpdateProfile(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters: allow other classes to read private attributes
    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
