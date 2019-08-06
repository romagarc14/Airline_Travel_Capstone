package com.company.airlinetravelcapstone.Controller;
import com.company.airlinetravelcapstone.DTO.Airlines;
import com.company.airlinetravelcapstone.DTO.Airports;
import com.company.airlinetravelcapstone.Service.AirlineService;
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

public class AirlineControllerTest {

    private MockMvc mockMvc;

    @Mock
    AirlineService mockAirlineService;

    @InjectMocks
    AirlineController airlineController;

    Airports airport1;
    Airports airport2;

    Airlines airline1;
    Airlines airline2;

    List<Airlines> airlineList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airlineController).build();

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
        public void ShouldReturnAllAirlines() throws Exception {
            when(mockAirlineService.getAllAirlines()).thenReturn(airlineList);


            mockMvc.perform(get("/airlines"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].name", is(airlineList.get(0).getName())));

            verify(mockAirlineService).getAllAirlines();

        }
    }
