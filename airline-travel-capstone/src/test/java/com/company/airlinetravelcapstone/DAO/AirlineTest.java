package com.company.airlinetravelcapstone.DAO;
import com.company.airlinetravelcapstone.DAO.AirlineRepository;
import com.company.airlinetravelcapstone.DAO.AirportRepository;

import com.company.airlinetravelcapstone.DTO.Airlines;
import com.company.airlinetravelcapstone.DTO.Airports;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirlineTest {
    @Autowired
    AirlineRepository airlineRepo;
    @Autowired
    AirportRepository airportRepo;

    Airports airport1;
    Airports airport2;

    Airlines airline1;
    Airlines airline2;

    @Before
    public void setUp(){

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
    }

    @Test
    @Transactional
    public void shouldAddAirlines(){
        airlineRepo.save(airline1);
        airlineRepo.save(airline2);

        assertNotNull(airline1.getId());
        assertNotNull(airline2.getId());
    }

    @Test
    @Transactional
    public void shouldGetAirlines() {

        airlineRepo.save(airline1);
        airport1.setAirlines(airline1);
        airport1.getAirlines().setId(airline1.getId());
        airportRepo.save(airport1);

        airport2.setAirlines(airline1);
        airport2.getAirlines().setId(airline1.getId());
        airportRepo.save(airport2);

        Set<Airports> airportsSet = new HashSet<>();
        airportsSet.add(airport1);
        airportsSet.add(airport2);

        List<Airlines> fromRepo = airlineRepo.findAll();

        assertEquals(airline1, fromRepo.get(0));
    }

    @Test
    @Transactional
    public void shouldDeleteAirlines() {
        airlineRepo.save(airline1);
        airlineRepo.deleteById(airline1.getId());

        Optional<Airlines> fromRepo = airlineRepo.findById(airline1.getId());
        assertFalse(fromRepo.isPresent());
    }

    @Test
    @Transactional
    public void shouldGetAirlineByName() {
        airlineRepo.save(airline1);
        airlineRepo.save(airline2);

        List<Airlines> airline1List = airlineRepo.findByName(airline1.getName());

        assertEquals(1, airline1List.size());

        List<Airlines> notPresentList = airlineRepo.findByName("Allegiant");
        assertEquals(0, notPresentList.size());
    }

    @After
    public void tearDown() {
        airlineRepo.deleteAll();
        airportRepo.deleteAll();
    }
}
