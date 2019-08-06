package com.company.airlinetravelcapstone.Controller;

import com.company.airlinetravelcapstone.DTO.Airlines;
import com.company.airlinetravelcapstone.DTO.Airports;
import com.company.airlinetravelcapstone.Service.AirportService;
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

public class AirportControllerTest {
    private MockMvc mockMvc;

    @Mock
    AirportService mockAirportService;

    @InjectMocks
    AirportController airportController;

    Airports airport1;
    Airports airport2;
    Airports airport3;

    Airlines airline1;
    Airlines airline2;

    List<Airports> airportList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();

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
    public void ShouldReturnAirportByCity() throws Exception {
        airportList = Arrays.asList(airport1);
        when(mockAirportService.getAirportByCity(airportList.get(0).getCity())).thenReturn(airportList);


        mockMvc.perform(get("/airports/city/" + airportList.get(0).getCity()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city", is(airportList.get(0).getCity())));

        verify(mockAirportService).getAirportByCity(airportList.get(0).getCity());

    }

}