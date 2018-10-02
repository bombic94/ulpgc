package es.ulpgc.functions;

public class Person {
    String name;
    String email;

    public Person(String name, String email) {
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
