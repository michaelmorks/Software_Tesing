package web.service;

/**
 * Business logic to handle login functions.
 * 
 * Improved version with full validation for:
 * - username
 * - password
 * - date of birth (dob)
 */
public class LoginService {

    /**
     * Returns true for successful login, false otherwise.
     */
    public static boolean login(String username, String password, String dob) {

        // 1. Null checks
        if (username == null || password == null || dob == null) {
            return false;
        }

        // 2. Trim inputs (remove spaces)
        username = username.trim();
        password = password.trim();
        dob = dob.trim();

        // 3. Empty field checks
        if (username.isEmpty() || password.isEmpty() || dob.isEmpty()) {
            return false;
        }

        // 4. Validate DOB format (yyyy-mm-dd)
        if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }

        // 5. Hardcoded valid credentials (for testing)
        if (username.equals("ahsan") &&
            password.equals("ahsan_pass") &&
            dob.equals("2000-01-01")) {
            return true;
        }

        // 6. Default fail
        return false;
    }
}