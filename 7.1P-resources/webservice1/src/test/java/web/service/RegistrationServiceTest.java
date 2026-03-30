package web.service;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationServiceTest {

    private static final String CHROME_DRIVER_PATH = "/Users/michaelmorks/Desktop/java_projects/chromedriver";
    private static final String REG_URL = "http://127.0.0.1:8082/reg";

    private void sleep(long sec) {
        try { Thread.sleep(sec * 1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        return new ChromeDriver();
    }

    private void submitForm(WebDriver driver, String fname, String lname,
                            String email, String dob, String password, String phone) {
        driver.get(REG_URL);
        sleep(2);
        driver.findElement(By.name("fname")).sendKeys(fname);
        driver.findElement(By.name("lname")).sendKeys(lname);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("dob")).sendKeys(dob);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("phone")).sendKeys(phone);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        sleep(2);
    }

    // ─── VALID REGISTRATION ─────────────────────────────────────────────

    @Test
    public void testValidRegistration() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678");
        Assert.assertEquals("success", driver.getTitle());
        driver.quit();
    }

    // ─── EMPTY FIELDS ───────────────────────────────────────────────────

    @Test
    public void testEmptyFirstName() {
        WebDriver driver = getDriver();
        submitForm(driver, "", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEmptyLastName() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "", "john@example.com", "2000-01-01", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEmptyEmail() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "", "2000-01-01", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEmptyDob() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@example.com", "", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEmptyPassword() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@example.com", "2000-01-01", "", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEmptyPhone() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@example.com", "2000-01-01", "secret123", "");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    // ─── INVALID NAME ───────────────────────────────────────────────────

    @Test
    public void testFirstNameWithNumbers() {
        WebDriver driver = getDriver();
        submitForm(driver, "John123", "Doe", "john@example.com", "2000-01-01", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testLastNameWithSymbols() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe!", "john@example.com", "2000-01-01", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    // ─── INVALID EMAIL ──────────────────────────────────────────────────

    @Test
    public void testEmailNoAtSymbol() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "johnexample.com", "2000-01-01", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEmailNoDot() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@examplecom", "2000-01-01", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    // ─── INVALID DOB ────────────────────────────────────────────────────

    @Test
    public void testDobWrongFormat_slashes() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@example.com", "01/01/2000", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testDobWrongFormat_noSeparator() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@example.com", "20000101", "secret123", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    // ─── INVALID PASSWORD ───────────────────────────────────────────────

    @Test
    public void testPasswordTooShort() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@example.com", "2000-01-01", "abc", "0412345678");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    // ─── INVALID PHONE ──────────────────────────────────────────────────

    @Test
    public void testPhoneTooShort() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@example.com", "2000-01-01", "secret123", "041234");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testPhoneWithLetters() {
        WebDriver driver = getDriver();
        submitForm(driver, "John", "Doe", "john@example.com", "2000-01-01", "secret123", "041234abcd");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }
}