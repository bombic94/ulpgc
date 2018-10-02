package es.ulpgc.reflection;


public class Main {

    public static void main(String[] args) throws AnimalFactory.AnimalNotKnownException, AnimalFactory.AnimalConstructionException {
        Animal animal = AnimalFactory.create("Whale");
        System.out.println(animal);
    }
}
