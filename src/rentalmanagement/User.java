package rentalmanagement;

import java.util.regex.Pattern;

public class User {

    // Attributes: information shared by property owners and tenants
    private int userID;
    private String firstName;
    private String lastName;
    private String email;

    // Week 3: validate ordinary names and a basic email format.
    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^[\\p{L}\\p{M}]+(?:[ '\\u2019-][\\p{L}\\p{M}]+)*$"
    );
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)+$"
    );

    // Constructor: validate all inputs before initializing attributes.
    public User(int userID, String firstName, String lastName, String email) {
        if (userID <= 0) {
            throw new IllegalArgumentException("User ID must be a positive integer.");
        }

        String validFirstName = validateName(firstName, "First name");
        String validLastName = validateName(lastName, "Last name");
        String validEmail = validateEmail(email);

        this.userID = userID;
        this.firstName = validFirstName;
        this.lastName = validLastName;
        this.email = validEmail;
    }

    // Week 3: shared validation for constructor and UpdateProfile().
    private static String validateName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }

        String trimmed = name.trim();
        if (!NAME_PATTERN.matcher(trimmed).matches()) {
            throw new IllegalArgumentException(fieldName + " contains invalid characters.");
        }
        return trimmed;
    }

    private static String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        String trimmed = email.trim();
        if (!EMAIL_PATTERN.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("Email format is invalid.");
        }
        return trimmed;
    }

    // GetFullName(): return first name and last name separated by a space
    public String GetFullName() {
        return firstName + " " + lastName;
    }

    // UpdateProfile(): validate all inputs before updating; keep userID unchanged.
    public void UpdateProfile(String firstName, String lastName, String email) {
        String validFirstName = validateName(firstName, "First name");
        String validLastName = validateName(lastName, "Last name");
        String validEmail = validateEmail(email);

        this.firstName = validFirstName;
        this.lastName = validLastName;
        this.email = validEmail;
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
