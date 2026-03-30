package web.service;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegistrationServiceUnitTest {

    // ─── CHECK 1: Null inputs ───────────────────────────────────────────

    @Test
    public void testNullFirstName() {
        assertFalse(RegistrationService.register(null, "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testNullLastName() {
        assertFalse(RegistrationService.register("John", null, "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testNullEmail() {
        assertFalse(RegistrationService.register("John", "Doe", null, "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testNullDob() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", null, "secret123", "0412345678"));
    }

    @Test
    public void testNullPassword() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", null, "0412345678"));
    }

    @Test
    public void testNullPhone() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "secret123", null));
    }

    @Test
    public void testAllNull() {
        assertFalse(RegistrationService.register(null, null, null, null, null, null));
    }

    // ─── CHECK 2: Empty inputs ──────────────────────────────────────────

    @Test
    public void testEmptyFirstName() {
        assertFalse(RegistrationService.register("", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testEmptyLastName() {
        assertFalse(RegistrationService.register("John", "", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testEmptyEmail() {
        assertFalse(RegistrationService.register("John", "Doe", "", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testEmptyDob() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "", "secret123", "0412345678"));
    }

    @Test
    public void testEmptyPassword() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "", "0412345678"));
    }

    @Test
    public void testEmptyPhone() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "secret123", ""));
    }

    @Test
    public void testWhitespaceFirstName() {
        assertFalse(RegistrationService.register("   ", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testWhitespaceLastName() {
        assertFalse(RegistrationService.register("John", "   ", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    // ─── CHECK 3: Name validation ───────────────────────────────────────

    @Test
    public void testFirstNameWithNumbers() {
        assertFalse(RegistrationService.register("John123", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testLastNameWithSymbols() {
        assertFalse(RegistrationService.register("John", "Doe!", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    // ─── CHECK 4: Email validation ──────────────────────────────────────

    @Test
    public void testEmailNoAtSymbol() {
        assertFalse(RegistrationService.register("John", "Doe", "johnexample.com", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testEmailNoDot() {
        assertFalse(RegistrationService.register("John", "Doe", "john@examplecom", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testEmailValid() {
        assertTrue(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    // ─── CHECK 5: DOB format ────────────────────────────────────────────

    @Test
    public void testDobWrongFormat_slashes() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "01/01/2000", "secret123", "0412345678"));
    }

    @Test
    public void testDobWrongFormat_noSeparator() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "20000101", "secret123", "0412345678"));
    }

    @Test
    public void testDobWrongFormat_letters() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "abcd-ef-gh", "secret123", "0412345678"));
    }

    // ─── CHECK 6: Password validation ──────────────────────────────────

    @Test
    public void testPasswordTooShort() {
        // less than 6 characters
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "abc", "0412345678"));
    }

    @Test
    public void testPasswordExactly6Chars() {
        // boundary value - exactly 6 characters should pass
        assertTrue(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "abc123", "0412345678"));
    }

    @Test
    public void testPasswordLongEnough() {
        assertTrue(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "securepassword", "0412345678"));
    }

    // ─── CHECK 7: Phone validation ──────────────────────────────────────

    @Test
    public void testPhoneTooShort() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "secret123", "041234"));
    }

    @Test
    public void testPhoneTooLong() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "secret123", "04123456789"));
    }

    @Test
    public void testPhoneWithLetters() {
        assertFalse(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "secret123", "041234abcd"));
    }

    @Test
    public void testPhoneExactly10Digits() {
        assertTrue(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    // ─── CHECK 8: Full valid registration ───────────────────────────────

    @Test
    public void testAllValidInputs() {
        assertTrue(RegistrationService.register("John", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }

    @Test
    public void testFirstNameWithSpaces() {
        // trim should handle leading/trailing spaces
        assertTrue(RegistrationService.register("  John  ", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678"));
    }
}