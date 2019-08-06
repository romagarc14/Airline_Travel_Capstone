package com.company.airlinetravelcapstone.Service;

import com.company.airlinetravelcapstone.DAO.AirlineRepository;
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
public class AirlineServiceTest {

    @Mock
    @Autowired
    AirlineRepository airlineRepoMock;

    @InjectMocks
    AirlineService airlineService;

    Airports airport1;
    Airports airport2;

    Airlines airline1;
    Airlines airline2;

    List<Airlines> airlineList;

    @Before
    public void setup(){
        airport1 = new Airports();
        airport1.setName("BOS");
        airport1.setCity("Boston");


        airport2 = new Airports();
        airport2.setName("MCO");
        airport2.setCity("Orlando");

        airline1 = new Airlines();
        airline1.setName("JetBlue");
        airline2 = new Airlines();
        airline2.setName("Frontier");

        airlineList = Arrays.asList(airline1, airline2);
    }

    @Test
    public void shouldGetAllAirlines(){
        List<Airlines> expectedList = Arrays.asList(airline1, airline2);
        when(airlineRepoMock.findAll()).thenReturn(airlineList);
        assertEquals(expectedList, airlineService.getAllAirlines());
    }
}
