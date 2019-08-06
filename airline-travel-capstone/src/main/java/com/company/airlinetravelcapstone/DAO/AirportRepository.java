package com.company.airlinetravelcapstone.DAO;

import com.company.airlinetravelcapstone.DTO.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airports, Integer>{
    List<Airports> findByName(String name);
    List<Airports> findByCity(String city);
    List<Airports> findByAirlineId(int airlineId);
}
