package com.company.airlinetravelcapstone.DAO;

import com.company.airlinetravelcapstone.DTO.Flights;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flights, Integer> {
    List<Flights> findByAirlineId(int airlineId);
    List<Flights> findByAirportName(String airportName);
    List<Flights> findByDestination(String destination);
    List<Flights> findByAirportNameAndAirlineId(String airportName, int airlineId);
    List<Flights> findByAirportNameAndDestination(String airportName, String destination, Sort sort);
    List<Flights> findByDestinationAndAirportName(String destination, String airportName, Sort sort);

}

