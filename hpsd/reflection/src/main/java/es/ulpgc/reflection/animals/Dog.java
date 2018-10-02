package es.ulpgc.reflection.animals;

import es.ulpgc.reflection.Animal;

public class Dog implements Animal {

    private String name;

    public Dog(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Dog{}";
    }
}
