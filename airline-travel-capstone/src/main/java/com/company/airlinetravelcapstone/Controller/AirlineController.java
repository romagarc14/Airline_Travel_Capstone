package com.company.airlinetravelcapstone.Controller;

import com.company.airlinetravelcapstone.DTO.Airlines;
import com.company.airlinetravelcapstone.Service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    //CRUD Operations
    @RequestMapping(value="/airlines", method= RequestMethod.POST)
    public Airlines addAirline(@RequestBody Airlines airlines) {
        airlineService.addAirline(airlines);
        return airlines;
    }

    @RequestMapping(value="/airlines", method=RequestMethod.GET)
    public List<Airlines> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @RequestMapping(value="/airlines/{id}", method=RequestMethod.GET)
    public Airlines getAirlineById(@PathVariable int id) {
        return airlineService.getAirlineById(id);
    }

    @RequestMapping(value="/airlines/{id}", method=RequestMethod.PUT)
    public void updateAirline(@RequestBody Airlines airlines, @PathVariable int id) {
        airlineService.updateAirline(airlines, id);
    }
    @RequestMapping(value="/airlines/{id}", method=RequestMethod.DELETE)
    public void deleteAirline(@PathVariable int id) {
        airlineService.deleteAirlines(id);
    }

    //Custom Operation
    @RequestMapping(value="/airlines/name/{name}", method=RequestMethod.GET)
    public List<Airlines> getAirlineByName(@PathVariable String name) {
        return airlineService.getAirlineByName(name);
    }

}
