package com.company.airlinetravelcapstone.DAO;

import com.company.airlinetravelcapstone.DAO.AirlineRepository;
import com.company.airlinetravelcapstone.DAO.FlightRepository;
import com.company.airlinetravelcapstone.DTO.Airlines;
import com.company.airlinetravelcapstone.DTO.Flights;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightTest {
    @Autowired
    AirlineRepository airlineRepo;
    @Autowired
    FlightRepository flightRepo;

    Flights flight1;
    Flights flight2;

    Airlines airline1;

    @Before
    public void setUp(){
        flightRepo.deleteAll();
        airlineRepo.deleteAll();

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
    }
    @Test
    @Transactional
    public void shouldAddFlights(){
        airlineRepo.save(airline1);
        flightRepo.save(flight1);
        flightRepo.save(flight2);

        assertNotNull(flight1.getId());
        assertNotNull(flight2.getId());
    }

    @Test
    @Transactional
    public void shouldGetFlights(){
        airlineRepo.save(airline1);
        flight1.setAllAirlines(airline1);
        flightRepo.save(flight1);

        flight2.setAllAirlines(airline1);
        flightRepo.save(flight2);

        List<Flights> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);

        List<Flights> fromRepo = flightRepo.findAll();
        assertEquals(flightList, fromRepo);
    }

    @Test
    @Transactional
    public void shouldDeleteFlights(){
        airlineRepo.save(airline1);
        flight1.setAllAirlines(airline1);
        flightRepo.save(flight1);
        flightRepo.deleteById(flight1.getId());
        Optional<Flights> fromRepo = flightRepo.findById(flight1.getId());
        assertFalse(fromRepo.isPresent());
    }

    @Test
    @Transactional
    public void shouldGetFlightsByDestination(){
        airlineRepo.save(airline1);
        flight1.setAllAirlines(airline1);
        flightRepo.save(flight1);

        flight2.setAllAirlines(airline1);
        flightRepo.save(flight2);

        List<Flights> flightList = flightRepo.findByDestination(flight2.getDestination());
        assertEquals(flight2, flightList.get(0));

    }

    @Test
    @Transactional
    public void shouldGetFlightByAirportNameAndAirlineId(){
        airlineRepo.save(airline1);
        flight1.setAllAirlines(airline1);
        flight1.getAllAirlines().setId(airline1.getId());
        flightRepo.save(flight1);

        flight2.setAllAirlines(airline1);
        flight2.getAllAirlines().setId(airline1.getId());
        flightRepo.save(flight2);

        List<Flights> fromRepo = flightRepo.findByAirportNameAndAirlineId(flight1.getAirportName(), flight1.getAllAirlines().getId());
        assertEquals(0, fromRepo.size());}

    @After
    public void tearDown() {
        flightRepo.deleteAll();
        airlineRepo.deleteAll();
    }
}
