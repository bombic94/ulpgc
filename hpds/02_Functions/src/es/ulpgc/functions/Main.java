package es.ulpgc.functions;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Juan", "juan@gmail.com"));
        list.add(new Person("Hector", "hector@ulpgc.es"));
        list.add(new Person("Jose", "jose@gmail.com"));

        List<String> result = list.stream()
                .map(person -> person.getEmail())
                .filter(email -> email.endsWith("gmail.com"))
                .collect(toList());

        result.forEach(System.out::println);
    }

}
