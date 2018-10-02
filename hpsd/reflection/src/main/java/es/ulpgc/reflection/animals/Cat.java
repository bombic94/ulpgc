package es.ulpgc.reflection.animals;

import es.ulpgc.reflection.Animal;

public class Cat implements Animal {

    private String name;

    public Cat(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Cat{}";
    }
}
