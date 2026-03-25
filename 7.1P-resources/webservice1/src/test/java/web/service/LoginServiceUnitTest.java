package web.service;
import org.junit.Test;
import static org.junit.Assert.*;
import web.service.LoginService;

public class LoginServiceUnitTest {

    // ─── CHECK 1: Null inputs ───────────────────────────────────────────

    @Test
    public void testNullUsername() {
        assertFalse(LoginService.login(null, "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testNullPassword() {
        assertFalse(LoginService.login("ahsan", null, "2000-01-01"));
    }

    @Test
    public void testNullDob() {
        assertFalse(LoginService.login("ahsan", "ahsan_pass", null));
    }

    @Test
    public void testAllNull() {
        assertFalse(LoginService.login(null, null, null));
    }

    // ─── CHECK 2: Empty inputs ──────────────────────────────────────────

    @Test
    public void testEmptyUsername() {
        assertFalse(LoginService.login("", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testEmptyPassword() {
        assertFalse(LoginService.login("ahsan", "", "2000-01-01"));
    }

    @Test
    public void testEmptyDob() {
        assertFalse(LoginService.login("ahsan", "ahsan_pass", ""));
    }

    @Test
    public void testWhitespaceOnlyUsername() {
        // trim() should make this empty → fail
        assertFalse(LoginService.login("   ", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testWhitespaceOnlyPassword() {
        assertFalse(LoginService.login("ahsan", "   ", "2000-01-01"));
    }

    @Test
    public void testWhitespaceOnlyDob() {
        assertFalse(LoginService.login("ahsan", "ahsan_pass", "   "));
    }

    // ─── CHECK 3: DOB format validation ────────────────────────────────

    @Test
    public void testDobWrongFormat_slashes() {
        // dd/mm/yyyy instead of yyyy-mm-dd
        assertFalse(LoginService.login("ahsan", "ahsan_pass", "01/01/2000"));
    }

    @Test
    public void testDobWrongFormat_noSeparator() {
        assertFalse(LoginService.login("ahsan", "ahsan_pass", "20000101"));
    }

    @Test
    public void testDobWrongFormat_letters() {
        assertFalse(LoginService.login("ahsan", "ahsan_pass", "abcd-ef-gh"));
    }

    @Test
    public void testDobWrongFormat_shortYear() {
        // only 2-digit year
        assertFalse(LoginService.login("ahsan", "ahsan_pass", "00-01-01"));
    }

    @Test
    public void testDobCorrectFormat() {
        // Valid format but wrong credentials overall — checks regex passes
        // (will still return false due to wrong username, but regex won't block it)
        assertFalse(LoginService.login("wronguser", "wrongpass", "1999-05-20"));
    }

    // ─── CHECK 4: Credential matching ──────────────────────────────────

    @Test
    public void testValidCredentials() {
        // The one correct combination
        assertTrue(LoginService.login("ahsan", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testWrongPassword() {
        assertFalse(LoginService.login("ahsan", "wrong_pass", "2000-01-01"));
    }

    @Test
    public void testWrongUsername() {
        assertFalse(LoginService.login("wronguser", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testWrongDob() {
        assertFalse(LoginService.login("ahsan", "ahsan_pass", "1999-12-31"));
    }

    @Test
    public void testAllWrong() {
        assertFalse(LoginService.login("bad", "bad", "1111-11-11"));
    }

    @Test
    public void testCaseSensitiveUsername() {
        // "Ahsan" != "ahsan" — should fail
        assertFalse(LoginService.login("Ahsan", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testCaseSensitivePassword() {
        // "Ahsan_Pass" != "ahsan_pass" — should fail
        assertFalse(LoginService.login("ahsan", "Ahsan_Pass", "2000-01-01"));
    }

    @Test
    public void testUsernameWithLeadingSpaces() {
        // trim() should handle this — "  ahsan  " becomes "ahsan" → should pass
        assertTrue(LoginService.login("  ahsan  ", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testPasswordWithLeadingSpaces() {
        // trim() → "ahsan_pass" → should pass
        assertTrue(LoginService.login("ahsan", "  ahsan_pass  ", "2000-01-01"));
    }
}
