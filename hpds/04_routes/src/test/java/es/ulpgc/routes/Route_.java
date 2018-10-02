package es.ulpgc.routes;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class Route_ {

    @Test
    public void when_airport_loader_is_not_set_should_throw_UndefinedAirportLoaderException() throws UnknownAirportException {
        assertThatThrownBy(Route_::withoutAirportLoader)
                .isInstanceOf(UndefinedAirportLoaderException.class);
    }

    private static void withoutAirportLoader() throws UnknownAirportException {
        Route.from("LPA").to("LPA");
    }

    @Test
    public void given_same_origin_and_destination_airport_should_return_no_flights() throws UnknownAirportException {
        AirportLoader airportLoader = mock(AirportLoader.class);
        when(airportLoader.exists("LPA")).thenReturn(true);

        Route.setAirportLoader(airportLoader);
        List<Flight> flights = Route.from("LPA").to("LPA");
        assertThat(flights.size()).isZero();
        verify(airportLoader, times(2)).exists("LPA");
    }

    @Test
    public void given_unknown_origin_airport_should_throw_UnknownAirportException() {
        assertThatThrownBy(Route_::fromUnknownOriginAirpot)
                .isInstanceOf(UnknownAirportException.class);
    }

    private static void fromUnknownOriginAirpot() throws UnknownAirportException {
        AirportLoader airportLoader = mock(AirportLoader.class);
        when(airportLoader.exists("XXX")).thenReturn(false);

        Route.setAirportLoader(airportLoader);
        List<Flight> flights = Route.from("XXX").to("LPA");
        assertThat(flights.size()).isZero();
    }

}
