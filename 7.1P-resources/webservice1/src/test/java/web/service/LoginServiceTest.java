package web.service;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginServiceTest {

	private static final String CHROME_DRIVER_PATH = "/Users/michaelmorks/Desktop/java_projects/chromedriver";
	private static final String LOGIN_URL = "http://127.0.0.1:8082/login";

    private void sleep(long sec) {
        try { Thread.sleep(sec * 1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        return new ChromeDriver();
    }

    private void submitForm(WebDriver driver, String username, String password, String dob) {
        driver.get(LOGIN_URL);
        sleep(2);
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("passwd")).sendKeys(password);
        driver.findElement(By.name("dob")).sendKeys(dob);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        sleep(2);
    }

    // ─── VALID LOGIN ────────────────────────────────────────────────────

    @Test
    public void testLoginSuccess() {
        WebDriver driver = getDriver();
        submitForm(driver, "ahsan", "ahsan_pass", "2000-01-01");
        Assert.assertEquals("success", driver.getTitle());
        driver.quit();
    }

    // ─── WRONG CREDENTIALS ──────────────────────────────────────────────

    @Test
    public void testWrongPassword() {
        WebDriver driver = getDriver();
        submitForm(driver, "ahsan", "wrong_pass", "2000-01-01");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testWrongUsername() {
        WebDriver driver = getDriver();
        submitForm(driver, "wronguser", "ahsan_pass", "2000-01-01");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testWrongDob() {
        WebDriver driver = getDriver();
        submitForm(driver, "ahsan", "ahsan_pass", "1999-12-31");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testAllWrongCredentials() {
        WebDriver driver = getDriver();
        submitForm(driver, "bad", "bad", "1111-11-11");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    // ─── EMPTY FIELDS ───────────────────────────────────────────────────

    @Test
    public void testEmptyUsername() {
        WebDriver driver = getDriver();
        submitForm(driver, "", "ahsan_pass", "2000-01-01");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEmptyPassword() {
        WebDriver driver = getDriver();
        submitForm(driver, "ahsan", "", "2000-01-01");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEmptyDob() {
        WebDriver driver = getDriver();
        submitForm(driver, "ahsan", "ahsan_pass", "");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }


    // ─── INVALID DOB FORMAT ─────────────────────────────────────────────

    @Test
    public void testDobWrongFormat_slashes() {
        WebDriver driver = getDriver();
        submitForm(driver, "ahsan", "ahsan_pass", "01/01/2000");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testDobWrongFormat_noSeparator() {
        WebDriver driver = getDriver();
        submitForm(driver, "ahsan", "ahsan_pass", "20000101");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testDobWrongFormat_letters() {
        WebDriver driver = getDriver();
        submitForm(driver, "ahsan", "ahsan_pass", "abcd-ef-gh");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    // ─── CASE SENSITIVITY ───────────────────────────────────────────────

    @Test
    public void testUsernameWrongCase() {
        WebDriver driver = getDriver();
        submitForm(driver, "Ahsan", "ahsan_pass", "2000-01-01");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testPasswordWrongCase() {
        WebDriver driver = getDriver();
        submitForm(driver, "ahsan", "Ahsan_Pass", "2000-01-01");
        Assert.assertEquals("fail", driver.getTitle());
        driver.quit();
    }
}