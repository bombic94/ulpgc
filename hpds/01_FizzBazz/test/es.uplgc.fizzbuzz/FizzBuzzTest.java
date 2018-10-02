package es.uplgc.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FizzBuzzTest {

    @Test
    public void given1ShouldReturn1() {
        assertThat(FizzBuzz.of(1), is("1"));
    }

    @Test
    public void given2ShouldReturn2() {
        assertThat(FizzBuzz.of(2), is("2"));
    }

    @Test
    public void given3ShouldReturnFizz() {
        assertThat(FizzBuzz.of(3), is("Fizz"));
    }

    @Test
    public void given5ShouldReturnBuzz() {
        assertThat(FizzBuzz.of(5), is("Buzz"));
    }

    @Test
    public void given6ShouldReturnFizz() {
        assertThat(FizzBuzz.of(6), is("Fizz"));
    }

    @Test
    public void given10ShouldReturnBuzz() {
        assertThat(FizzBuzz.of(10), is("Buzz"));
    }

    @Test
    public void given15ShouldReturnFizzBuzz() {
        assertThat(FizzBuzz.of(15), is("FizzBuzz"));
    }

    public static class FizzBuzz {
        public static String of(int number) {
            return (fizzbuzz(number).isEmpty()) ? Integer.toString(number) : fizzbuzz(number);
        }

        public static String fizzbuzz(int number) {
            return fizz(number) + buzz(number);
        }

        public static String fizz(int number) {
            return isMultipleOfThree(number) ? "Fizz" : "";
        }

        public static String buzz(int number) {
            return isMultipleOfFive(number) ? "Buzz" : "";
        }

        private static boolean isMultipleOfThree(int number) {
            return number % 3 == 0;
        }

        private static boolean isMultipleOfFive(int number) {
            return number % 5 == 0;
        }

    }
}
