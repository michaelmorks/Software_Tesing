package sit707_week6;

public class WeatherAndMathUtils {
    public static final double DANGEROUS_WINDSPEED = 70.0;
    public static final double DANGEROUS_RAINFALL = 6.0;
    public static final double CONCERNING_WINDSPEED = 45.0;
    public static final double CONCERNING_RAINFALL = 4.0;

    /**
     * Advises weather alert based on wind-speed and precipitation.
     */
    public static String weatherAdvice(double windSpeed, double precipitation) {
        if (windSpeed < 0 || precipitation < 0) {
            throw new IllegalArgumentException("Invalid weather values");
        }

        String advice = "ALL CLEAR";

        if (windSpeed > DANGEROUS_WINDSPEED || precipitation > DANGEROUS_RAINFALL
                || (windSpeed > CONCERNING_WINDSPEED && precipitation > CONCERNING_RAINFALL)) {
            advice = "CANCEL";
        } else if (windSpeed > CONCERNING_WINDSPEED || precipitation > CONCERNING_RAINFALL) {
            advice = "WARN";
        }

        return advice;
    }

    /**
     * Checks if a number is even.
     */
    public static boolean isEven(int a) {
        return a % 2 == 0;
    }

    /**
     * Checks if a number is prime.
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false; // 1 and below are NOT prime
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false; // divisible → not prime
            }
        }

        return true; // prime
    }
}
