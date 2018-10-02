package eii.ulpgc;

import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.*;

public class StringCalculator {

    public static int add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        }
        String delimiter = ",\n";
        if (startsWith(numbers,"//")) {
            delimiter = substringBetween(numbers, "//", "\n");
            numbers = substringAfter(numbers, "\n");
        }

        return Arrays.asList(split(numbers, delimiter)).stream().mapToInt(s -> parseInt(s)).sum();
    }
}
