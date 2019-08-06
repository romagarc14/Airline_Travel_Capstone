package com.company.airlinetravelcapstone.Service;

import com.company.airlinetravelcapstone.DAO.FlightRepository;
import com.company.airlinetravelcapstone.DTO.Airlines;
import com.company.airlinetravelcapstone.DTO.Flights;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {
    @Mock
    @Autowired
    FlightRepository flightRepoMock;

    @InjectMocks
    FlightService flightService;

    Flights flight1;
    Flights flight2;

    Airlines airline1;

    List<Flights> flightList;

    @Before
    public void setUp(){

        flight1 = new Flights();
        flight1.setAirportName("BOS");
        flight1.setDestination("Orlando");
        flight1.setDistance(1116);
        flight1.setMinutesTraveled(150);
        flight1.setNumOfStops(0);


        flight2 = new Flights();
        flight2.setAirportName("MCO");
        flight2.setDestination("Boston");
        flight2.setDistance(1116);
        flight2.setMinutesTraveled(150);
        flight2.setNumOfStops(0);


        airline1 = new Airlines();
        airline1.setName("JetBlue");

        flightList = Arrays.asList(flight1, flight2);
    }

    @Test
    public void shouldGetFlightsByDestination(){
        List<Flights> expectedList = Arrays.asList(flight2);
        List<Flights> flightList= Arrays.asList(flight2);
        when(flightRepoMock.findByDestination(flight2.getDestination())).thenReturn(flightList);
        assertEquals(expectedList, flightService.getFlightsByDestination(flight2.getDestination()));
    }
}


