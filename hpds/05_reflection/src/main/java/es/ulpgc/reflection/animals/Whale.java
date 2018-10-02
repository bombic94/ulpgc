package es.ulpgc.reflection.animals;

import es.ulpgc.reflection.Animal;

public class Whale implements Animal {

    private String name;

    public Whale() {
        this.name = "";
    }

    public Whale(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Whale{"+ name + "}";
    }
}
