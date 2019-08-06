package com.company.airlinetravelcapstone.DAO;

import com.company.airlinetravelcapstone.DAO.AirlineRepository;
import com.company.airlinetravelcapstone.DAO.AirportRepository;
import com.company.airlinetravelcapstone.DTO.Airlines;
import com.company.airlinetravelcapstone.DTO.Airports;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportTest {
    @Autowired
    AirportRepository airportRepo;
    @Autowired
    AirlineRepository airlineRepo;

    Airports airport1;
    Airports airport2;
    Airports airport3;

    Airlines airline1;
    Airlines airline2;

    @Before
    public void setUp(){
        airportRepo.deleteAll();
        airlineRepo.deleteAll();

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
    }

    @Test
    @Transactional
    public void shouldAddAirports(){
        airlineRepo.save(airline1);
        airportRepo.save(airport1);
        airportRepo.save(airport2);

        assertNotNull(airport1.getId());
        assertNotNull(airport2.getId());
    }

    @Test
    @Transactional
    public void shouldGetAirports(){
        airlineRepo.save(airline1);

        airport1.setAirlines(airline1);
        airportRepo.save(airport1);

        airport2.setAirlines(airline1);
        airportRepo.save(airport2);

        List<Airports> airportList = new ArrayList<>();
        airportList.add(airport1);
        airportList.add(airport2);

        List<Airports> fromRepo = airportRepo.findAll();

        assertEquals(airportList, fromRepo);
    }

    @Test
    @Transactional
    public void shouldDeleteAirports(){
        airlineRepo.save(airline1);
        airport1.setAirlines(airline1);
        airportRepo.save(airport1);
        airportRepo.deleteById(airport1.getId());
        Optional<Airports> fromRepo = airportRepo.findById(airport1.getId());
        assertFalse(fromRepo.isPresent());
    }

    @Test
    @Transactional
    public void shouldGetAirportsByCity(){
        airlineRepo.save(airline1);
        airport1.setAirlines(airline1);
        airportRepo.save(airport1);

        airport2.setAirlines(airline1);
        airportRepo.save(airport2);

        airlineRepo.save(airline2);
        airport3.setAirlines(airline2);
        airportRepo.save(airport3);

    List<Airports> airportList = airportRepo.findByCity(airport3.getCity());
    assertEquals(airport3, airportList.get(0));

    }

    @After
    public void tearDown() {
        airportRepo.deleteAll();
        airlineRepo.deleteAll();
    }
}
