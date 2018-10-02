package es.ulpgc.routes;

import java.util.List;

import static java.util.Collections.emptyList;

public class Route {
    private static AirportLoader airportLoader;
    private String origin;

    public Route(String origin) {
        this.origin = origin;
    }

    public static Route from(String airport) throws UnknownAirportException {
        if (airportLoader == null) throw new UndefinedAirportLoaderException();
        if (!airportLoader.exists(airport)) throw new UnknownAirportException();
        return new Route(airport);
    }

    public static void setAirportLoader(AirportLoader airportLoader) {
        Route.airportLoader = airportLoader;
    }

    public List<Flight> to(String airport) throws UnknownAirportException {
        if (!airportLoader.exists(airport)) throw new UnknownAirportException();
        return emptyList();
    }
}
