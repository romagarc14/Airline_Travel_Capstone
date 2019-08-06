package com.company.airlinetravelcapstone.Controller;

import com.company.airlinetravelcapstone.DTO.Flights;
import com.company.airlinetravelcapstone.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
public class FlightController {
    @Autowired
    private FlightService flightService;

    //CRUD Operations
   @RequestMapping(value="/flights", method= RequestMethod.POST)
    public Flights addFlight(@RequestBody Flights flights) {
    flightService.addFlight(flights);
    return flights;
   }
    @RequestMapping(value="/flights/{id}", method=RequestMethod.PUT)
    public void updateFlight(@RequestBody Flights flights, @PathVariable int id) {
        flightService.updateFlight(flights, id);
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public List<Flights> getAllFlights() {
        return flightService.getAllFlights();
    }

    @RequestMapping(value = "/flights/{id}", method = RequestMethod.GET)
    public Flights getFlightById(@PathVariable int id) {
        return flightService.getFlightById(id);
    }

    @RequestMapping(value = "/flights/{id}", method = RequestMethod.DELETE)
    public void deleteFlights(@PathVariable int id) {
        flightService.deleteFlights(id);
    }


    //Custom Operations
    @RequestMapping(value = "/flights/price", method = RequestMethod.GET)
    public List<Flights> getAllFlightsByPrice(){
       return flightService.getAllFlightsByPrice();
    }

    @RequestMapping(value = "/flights/time", method = RequestMethod.GET)
    public List<Flights> getAllFlightsByMinutesTraveled(){
       return flightService.getAllFlightsByMinutesTraveled();
    }

    @GetMapping(value = "/flights/airlines/{airlineId}")
    public List<Flights> getFlightByAirlineId(@PathVariable int airlineId) {
        return flightService.getFlightsByAirlineId(airlineId);
    }
    @GetMapping(value = "/flights/airports/{airportName}")
    public List<Flights> getFlightByAirportName(@PathVariable String airportName) {
        return flightService.getFlightsByAirportName(airportName);
    }

    @GetMapping(value = "/flights/destination/{destination}")
    public List<Flights> getFlightsByDestination(@PathVariable String destination) {
        return flightService.getFlightsByDestination(destination);
    }

    @GetMapping(value = "/flights/{airportName}/{airlineId}")
    public List<Flights> getFlightsByAirportAndAirline(@PathVariable String airportName, @PathVariable int airlineId) {
        return flightService.getFlightsByAirportAndAirline(airportName, airlineId);
    }

    @GetMapping(value = "/cheap/{airportName}/{destination}")
    public List<Flights> getFlightsByAirportAndDestination(@PathVariable String airportName, @PathVariable String destination){
       return flightService.getFlightsByAirportAndDestination(airportName, destination);
    }

    @GetMapping(value = "/short/{destination}/{airportName}")
    public List<Flights> getFlightTimeByAirportAndDestination(@PathVariable String destination, @PathVariable String airportName){
       return flightService.getFlightTimeByAirportAndDestination(destination, airportName);
    }

}