package com.company.airlinetravelcapstone.Controller;

import com.company.airlinetravelcapstone.DTO.Airports;
import com.company.airlinetravelcapstone.Service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AirportController {
    @Autowired
    private AirportService airportService;

    //CRUD Operations
    @RequestMapping(value="/airports", method= RequestMethod.POST)
    public Airports addAirport(@RequestBody Airports airports) {
        return airportService.addAirport(airports);
    }

    @RequestMapping(value="/airports", method=RequestMethod.GET)
    public List<Airports> getAllAirports() {
        return airportService.getAllAirports();
    }

    @RequestMapping(value="/airports/{id}", method=RequestMethod.GET)
    public Airports getAirportById(@PathVariable int id) {
        return airportService.getAirportById(id);
    }

    @RequestMapping(value="/airports/{id}", method=RequestMethod.PUT)
    public void updateAirport(@RequestBody Airports airports, @PathVariable int id) {
        airportService.updateAirport(airports, id);
    }

    @RequestMapping(value="/airports/{id}", method=RequestMethod.DELETE)
    public void deleteAirport(@PathVariable int id) {
        airportService.deleteAirports(id);
    }


    //Custom Operations
    @GetMapping(value = "/airports/name/{name}")
    public List<Airports> getAirportByName(@PathVariable String name) {
        return airportService.getAirportByName(name);
    }

    @GetMapping(value = "/airports/city/{city}")
    public List<Airports> getAirportByCity(@PathVariable String city) {
        return airportService.getAirportByCity(city);
    }

    @GetMapping(value = "/airports/airlines/{airlineId}")
    public List<Airports> getAirportByAirlineId(@PathVariable int airlineId){
        return airportService.getAirportByAirlineId(airlineId);
    }
}
