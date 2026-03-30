package web.service;

/**
 * Business logic to handle registration functions.
 */
public class RegistrationService {

    public static boolean register(
            String fName, String lName, String email, String dob, String password, String phone) {

        // 1. Null checks
        if (fName == null || lName == null || email == null ||
            dob == null || password == null || phone == null) {
            return false;
        }

        // 2. Trim inputs
        fName    = fName.trim();
        lName    = lName.trim();
        email    = email.trim();
        dob      = dob.trim();
        password = password.trim();
        phone    = phone.trim();

        // 3. Empty field checks
        if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() ||
            dob.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            return false;
        }

        // 4. First and last name must be letters only (no numbers/symbols)
        if (!fName.matches("[a-zA-Z]+") || !lName.matches("[a-zA-Z]+")) {
            return false;
        }

        // 5. Email must contain @ and a dot after @
        if (!email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
            return false;
        }

        // 6. DOB format must be yyyy-mm-dd
        if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }

        // 7. Password must be at least 6 characters
        if (password.length() < 6) {
            return false;
        }

        // 8. Phone must be digits only and 10 digits long
        if (!phone.matches("\\d{10}")) {
            return false;
        }

        // All validations passed
        return true;
    }
}