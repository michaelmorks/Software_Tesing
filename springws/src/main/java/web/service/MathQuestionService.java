package web.service;

public class MathQuestionService {

    public static Double safeParse(String value) {
        try {
            if (value == null || value.trim().isEmpty()) {
                return null;
            }
            return Double.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static double q1Addition(String number1, String number2) {
        Double n1 = safeParse(number1);
        Double n2 = safeParse(number2);
        if (n1 == null || n2 == null) {
            throw new IllegalArgumentException("Invalid input: non-numeric or empty value provided.");
        }
        return n1 + n2;
    }

    public static double q2Subtraction(String number1, String number2) {
        Double n1 = safeParse(number1);
        Double n2 = safeParse(number2);
        if (n1 == null || n2 == null) {
            throw new IllegalArgumentException("Invalid input: non-numeric or empty value provided.");
        }
        return n1 - n2;
    }

    public static double q3Multiplication(String number1, String number2) {
        Double n1 = safeParse(number1);
        Double n2 = safeParse(number2);
        if (n1 == null || n2 == null) {
            throw new IllegalArgumentException("Invalid input: non-numeric or empty value provided.");
        }
        return n1 * n2;
    }
}