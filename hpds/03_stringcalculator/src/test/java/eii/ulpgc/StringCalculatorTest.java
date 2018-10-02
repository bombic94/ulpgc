package eii.ulpgc;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StringCalculatorTest {

    public StringCalculator calculator;

    @Before
    public void init() {
        calculator = new StringCalculator();
    }

    @Test
    public void givenEmptyStringShouldReturn0() {
        assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void givenXShouldReturnX() {
        assertThat(calculator.add("1")).isEqualTo(1);
    }

    @Test
    public void givenXandYShouldReturnXPlusY() {
        assertThat(calculator.add("1,2")).isEqualTo(3);
    }

    @Test
    public void givenMoreNumbersShouldReturnSum() {
        assertThat(calculator.add("1,2,3")).isEqualTo(6);
    }

    @Test
    public void givenMoreNumbersShouldReturnSum2() {
        assertThat(calculator.add("1,2,3,4,5")).isEqualTo(15);
    }

    @Test
    public void givenXandYLineShouldReturnXPlusY() {
        assertThat(calculator.add("1\n2")).isEqualTo(3);
    }

    @Test
    public void givenMoreNumbersLineShouldReturnSum() {
        assertThat(calculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void givenMoreNumbersLineShouldReturnSum2() {
        assertThat(calculator.add("1\n2,3\n4,5")).isEqualTo(15);
    }

    @Test
    public void differentDelimiters1() {
        assertThat(calculator.add("//;\n1;2;3;4;5")).isEqualTo(15);
    }

    @Test
    public void differentDelimiters2() {
        assertThat(calculator.add("//-\n1-2-3-4-5")).isEqualTo(15);
    }

    @Test
    public void negativeNumbers1() {
        assertThat(calculator.add("1,-2,3,4,5")).isEqualTo("negatives not allowed: -2");
    }

    @Test
    public void negativeNumbers2() {
        assertThat(calculator.add("1,-2,-3,4,5")).isEqualTo("negatives not allowed: -2,-3");
    }

    @Test
    public void negativeNumbers3() {
        assertThat(calculator.add("//;\n1;2;-3;-4;-5")).isEqualTo("negatives not allowed: -3,-4,-5");
    }
}
