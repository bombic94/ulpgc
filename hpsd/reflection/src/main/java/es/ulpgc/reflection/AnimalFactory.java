package es.ulpgc.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class AnimalFactory {

    public static Animal create(String type) throws AnimalNotKnownException, AnimalConstructionException {
        try {
            return (Animal) classOf(type).newInstance();
        } catch (ClassNotFoundException e) {
            throw new AnimalNotKnownException();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new AnimalConstructionException();
        }

    }

    public static Animal create(String type, String name) throws AnimalNotKnownException, AnimalConstructionException {
        try {
            return (Animal) nameConstructorOf(classOf(type)).newInstance(name);
        } catch (ClassNotFoundException e) {
            throw new AnimalNotKnownException();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new AnimalConstructionException();
        }

    }

    private static Constructor nameConstructorOf(Class<?> class_) throws AnimalConstructionException {
        try {
            return Arrays.stream(class_.getConstructors())
                    .filter(c->c.getParameterCount() == 1)
                    .filter(c->c.getParameterTypes()[0] == String.class)
                    .findFirst()
                    .get();
        }
        catch (NoSuchElementException e) {
            throw new AnimalConstructionException();
        }
    }

    private static Class<?> classOf(String type) throws ClassNotFoundException {
        return Class.forName("es.ulpgc.reflection.animals." + type);
    }

    public static class AnimalNotKnownException extends Throwable {
    }


    public static class AnimalConstructionException extends Throwable {
    }
}
