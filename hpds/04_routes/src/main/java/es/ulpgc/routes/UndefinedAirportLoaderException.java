package es.ulpgc.routes;

public class UndefinedAirportLoaderException extends RuntimeException{

    public UndefinedAirportLoaderException() {
        super("Route requires that an airportLoader object should be injected");
    }
}
