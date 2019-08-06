package com.company.airlinetravelcapstone.Service;

import com.company.airlinetravelcapstone.DAO.AirportRepository;
import com.company.airlinetravelcapstone.DTO.Airlines;
import com.company.airlinetravelcapstone.DTO.Airports;
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
public class AirportServiceTest {
    @Mock
    @Autowired
    AirportRepository airportRepoMock;

    @InjectMocks
    AirportService airportService;
    Airports airport1;
    Airports airport2;
    Airports airport3;

    Airlines airline1;
    Airlines airline2;

    List<Airports> airportList;

    @Before
    public void setup() {
        airport1 = new Airports();
        airport1.setName("BOS");
        airport1.setCity("Boston");

        airport2 = new Airports();
        airport2.setName("MCO");
        airport2.setCity("Orlando");

        airport3 = new Airports();
        airport3.setName("LAS");
        airport3.setCity("Las Vegas");

        airline1 = new Airlines();
        airline1.setName("JetBlue");
        airline2 = new Airlines();
        airline2.setName("Frontier");

        airportList = Arrays.asList(airport1, airport2, airport3);
    }

        @Test
        public void shouldGetAirportsByName(){
            List<Airports> expectedList = Arrays.asList(airport1);
            List<Airports> airportList= Arrays.asList(airport1);
            when(airportRepoMock.findByName(airport1.getName())).thenReturn(airportList);
            assertEquals(expectedList, airportService.getAirportByName(airport1.getName()));
        }
}


