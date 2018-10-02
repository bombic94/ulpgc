package es.ulpgc.reflection.animals;

import es.ulpgc.reflection.Animal;

public class Bird implements Animal {

    private String name;

    public Bird() {
    }

    public Bird(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bird{}";
    }
}
