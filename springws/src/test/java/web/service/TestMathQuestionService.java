package web.service;

import org.junit.Assert;
import org.junit.Test;

public class TestMathQuestionService {

    // ── safeParse ──────────────────────────────────────────────────────────────

    @Test
    public void testSafeParse_ValidInteger() {
        Assert.assertEquals(5.0, MathQuestionService.safeParse("5"), 0.0001);
    }

    @Test
    public void testSafeParse_ValidDecimal() {
        Assert.assertEquals(3.14, MathQuestionService.safeParse("3.14"), 0.0001);
    }

    @Test
    public void testSafeParse_EmptyString() {
        Assert.assertNull(MathQuestionService.safeParse(""));
    }

    @Test
    public void testSafeParse_NullInput() {
        Assert.assertNull(MathQuestionService.safeParse(null));
    }

    @Test
    public void testSafeParse_LetterInput() {
        Assert.assertNull(MathQuestionService.safeParse("abc"));
    }

    @Test
    public void testSafeParse_WhitespaceOnly() {
        Assert.assertNull(MathQuestionService.safeParse("   "));
    }

    @Test
    public void testSafeParse_NegativeNumber() {
        Assert.assertEquals(-7.0, MathQuestionService.safeParse("-7"), 0.0001);
    }

    // ── q1Addition ─────────────────────────────────────────────────────────────

    @Test
    public void testQ1Addition_Basic() {
        Assert.assertEquals(3.0, MathQuestionService.q1Addition("1", "2"), 0.0001);
    }

    @Test
    public void testQ1Addition_LargeNumbers() {
        Assert.assertEquals(350.0, MathQuestionService.q1Addition("100", "250"), 0.0001);
    }

    @Test
    public void testQ1Addition_Decimals() {
        Assert.assertEquals(4.0, MathQuestionService.q1Addition("1.5", "2.5"), 0.0001);
    }

    @Test
    public void testQ1Addition_AddZero() {
        Assert.assertEquals(7.0, MathQuestionService.q1Addition("7", "0"), 0.0001);
    }

    @Test
    public void testQ1Addition_BothZero() {
        Assert.assertEquals(0.0, MathQuestionService.q1Addition("0", "0"), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_EmptyInput() {
        MathQuestionService.q1Addition("", "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_LetterInput() {
        MathQuestionService.q1Addition("a", "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_NullInput() {
        MathQuestionService.q1Addition(null, "2");
    }

    // ── q2Subtraction ──────────────────────────────────────────────────────────

    @Test
    public void testQ2Subtraction_Basic() {
        Assert.assertEquals(7.0, MathQuestionService.q2Subtraction("10", "3"), 0.0001);
    }

    @Test
    public void testQ2Subtraction_ResultZero() {
        Assert.assertEquals(0.0, MathQuestionService.q2Subtraction("5", "5"), 0.0001);
    }

    @Test
    public void testQ2Subtraction_NegativeResult() {
        Assert.assertEquals(-7.0, MathQuestionService.q2Subtraction("3", "10"), 0.0001);
    }

    @Test
    public void testQ2Subtraction_Decimals() {
        Assert.assertEquals(3.0, MathQuestionService.q2Subtraction("5.5", "2.5"), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_EmptyInput() {
        MathQuestionService.q2Subtraction("5", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_LetterInput() {
        MathQuestionService.q2Subtraction("xyz", "3");
    }

    // ── q3Multiplication ───────────────────────────────────────────────────────

    @Test
    public void testQ3Multiplication_Basic() {
        Assert.assertEquals(12.0, MathQuestionService.q3Multiplication("4", "3"), 0.0001);
    }

    @Test
    public void testQ3Multiplication_ByZero() {
        Assert.assertEquals(0.0, MathQuestionService.q3Multiplication("9", "0"), 0.0001);
    }

    @Test
    public void testQ3Multiplication_ByOne() {
        Assert.assertEquals(7.0, MathQuestionService.q3Multiplication("7", "1"), 0.0001);
    }

    @Test
    public void testQ3Multiplication_Decimals() {
        Assert.assertEquals(10.0, MathQuestionService.q3Multiplication("2.5", "4"), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_EmptyInput() {
        MathQuestionService.q3Multiplication("", "3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_LetterInput() {
        MathQuestionService.q3Multiplication("4", "abc");
    }
}
