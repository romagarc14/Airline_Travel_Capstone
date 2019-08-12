package com.company.airlinetravelcapstone.Service;

import com.company.airlinetravelcapstone.DAO.FlightRepository;
import com.company.airlinetravelcapstone.DTO.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.List;

@Component
public class FlightService {
    @Autowired
    private FlightRepository flightRepo;

    //CRUD Operations
    public List<Flights> getAllFlights() {
        return flightRepo.findAll();
    }

    public Flights getFlightById(int id) {
        return flightRepo.getOne(id);
    }

    public void updateFlight(Flights flights, int id) {
        flightRepo.save(flights);
    }

    public void deleteFlights(int id) {
        flightRepo.deleteById(id);
    }

    //Custom Operations
    public List<Flights> getAllFlightsByPrice(){
        return flightRepo.findAll(Sort.by(Sort.Direction.ASC,"price"));
    }

    public List<Flights> getAllFlightsByTotalTime(){
        return flightRepo.findAll(Sort.by(Sort.Direction.ASC,"totalTime"));
    }

    public List<Flights> getFlightsByAirlineId(int airlineId){
        return flightRepo.findByAirlineId(airlineId);
    }

    public List<Flights> getFlightsByAirportName(String airportName){
        return flightRepo.findByAirportName(airportName);
    }
    public List<Flights> getFlightsByDestination(String destination){
        return flightRepo.findByDestination(destination);
    }

    public List<Flights> getFlightsByAirportAndAirline(String airportName, int airlineId) {
        return flightRepo.findByAirportNameAndAirlineId(airportName, airlineId);
    }
    public List<Flights> getFlightsByAirportAndDestination(String airportName, String destination) {
        return flightRepo.findByAirportNameAndDestination(airportName, destination, Sort.by(Sort.Direction.ASC, "price"));
    }

    public List<Flights> getFlightTimeByAirportAndDestination(String destination, String airportName) {
       return flightRepo.findByDestinationAndAirportName(destination, airportName, Sort.by(Sort.Direction.ASC, "totalTime"));
    }


    //Price Calculation
    public Flights addFlight(Flights flights){
        String airportName = flights.getAirportName();

        int numOfStops = flights.getNumOfStops();
        int minutesTraveled = flights.getMinutesTraveled();
        minutesTraveled += (45 * numOfStops);

        int distance = flights.getDistance();
        int airlineId = flights.getAirlineId();
        int multiplier = 50;
        double rate;
            switch(airlineId){
                case 1:
                    rate = 0.95;
                    break;
                case 2:
                    rate = 1.0;
                    break;
                case 3:
                    rate = 1.1;
                    break;
                case 4:
                    rate = 0.85;
                    break;
                case 5:
                    rate = 1.05;
                    break;
                default:
                    rate = 0.9;
            }

        double base = (rate * distance * multiplier)/ minutesTraveled;
        int totalTime = minutesTraveled;
        BigDecimal price = BigDecimal.valueOf(base).setScale(2, RoundingMode.HALF_UP);

        flights.setTotalTime(totalTime);
        flights.setPrice(price);
        flightRepo.save(flights);
        return flights;
    }
}
