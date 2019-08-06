package com.company.airlinetravelcapstone.Controller;

import com.company.airlinetravelcapstone.DTO.Airlines;
import com.company.airlinetravelcapstone.DTO.Flights;
import com.company.airlinetravelcapstone.Service.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FlightControllerTest {
    private MockMvc mockMvc;

    @Mock
    FlightService mockFlightService;

    @InjectMocks
    FlightController flightController;

    Flights flight1;
    Flights flight2;

    Airlines airline1;

    List<Flights> flightList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();

        flight1 = new Flights();
        flight1.setAirportName("BOS");
        flight1.setNumOfStops(0);
        flight1.setMinutesTraveled(150);
        flight1.setDistance(1116);
        flight1.setDestination("Orlando");

        flight2 = new Flights();
        flight2.setAirportName("MCO");
        flight2.setNumOfStops(0);
        flight2.setMinutesTraveled(150);
        flight2.setDistance(1116);
        flight2.setDestination("Boston");

        airline1 = new Airlines();
        airline1.setName("JetBlue");

        flightList = Arrays.asList(flight1, flight2);
    }

    @Test
    public void ShouldReturnFlightByAirportName() throws Exception {
        flightList = Arrays.asList(flight1);
        when(mockFlightService.getFlightsByAirportName(flightList.get(0).getAirportName())).thenReturn(flightList);


        mockMvc.perform(get("/flights/airports/" + flightList.get(0).getAirportName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].airportName", is(flightList.get(0).getAirportName())));

        verify(mockFlightService).getFlightsByAirportName(flightList.get(0).getAirportName());

    }
}
