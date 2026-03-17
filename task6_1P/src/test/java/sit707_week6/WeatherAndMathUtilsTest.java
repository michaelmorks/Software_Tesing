package sit707_week6;

import org.junit.Assert;
import org.junit.Test;

public class WeatherAndMathUtilsTest {

    // Student identity tests
    @Test
    public void testStudentIdentity() {
        String studentId = "s224437029";
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Michael Morks";
        Assert.assertNotNull("Student name is null", studentName);
    }

    // isEven tests
    @Test
    public void testFalseNumberIsEven() {
        Assert.assertFalse(WeatherAndMathUtils.isEven(3));
    }

    @Test
    public void testTrueNumberIsEven() {
        Assert.assertTrue(WeatherAndMathUtils.isEven(4));
    }

    @Test
    public void testZeroEven() {
        Assert.assertTrue(WeatherAndMathUtils.isEven(0));
    }

    @Test
    public void testNegativeEven() {
        Assert.assertTrue(WeatherAndMathUtils.isEven(-4));
    }

    // isPrime tests
    @Test
    public void testPrimeNumber() {
        Assert.assertTrue(WeatherAndMathUtils.isPrime(3));
    }

    @Test
    public void testEvenNumberNotPrime() {
        Assert.assertFalse(WeatherAndMathUtils.isPrime(4));
    }

    @Test
    public void testOneIsNotPrime() {
        Assert.assertFalse(WeatherAndMathUtils.isPrime(1));
    }

    @Test
    public void testPrimeTwo() {
        Assert.assertTrue(WeatherAndMathUtils.isPrime(2));
    }

    @Test
    public void testPrimeLargePrime() {
        Assert.assertTrue(WeatherAndMathUtils.isPrime(13));
    }

    @Test
    public void testCompositeNumber() {
        Assert.assertFalse(WeatherAndMathUtils.isPrime(9));
    }

    // Weather advice tests
    @Test
    public void testWarnWind() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(50.0, 1.0));
    }

    @Test
    public void testWarnRain() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(10.0, 5.0));
    }

    @Test
    public void testCancelHeavyRain() {
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(10.0, 7.0));
    }

    @Test
    public void testCancelWindAndRain() {
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(50.0, 5.0));
    }

    @Test
    public void testAllClearWeather() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(10.0, 1.0));
    }

    @Test
    public void testCancelWeatherAdvice() {
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(70.1, 0.0));
    }

    // Boundary tests for BICEP
    @Test
    public void testDangerousWindBoundary() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(45.1, 1.0));
    }

    @Test
    public void testRainBoundary() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(10.0, 4.1));
    }

    // Error condition test
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeWeatherValues() {
        WeatherAndMathUtils.weatherAdvice(-1, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativePrecipitation() {
        WeatherAndMathUtils.weatherAdvice(5, -1);
    }
}
