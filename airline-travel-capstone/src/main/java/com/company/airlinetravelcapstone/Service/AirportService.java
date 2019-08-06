package com.company.airlinetravelcapstone.Service;

import com.company.airlinetravelcapstone.DAO.AirportRepository;
import com.company.airlinetravelcapstone.DTO.Airports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportService {
    @Autowired
    private AirportRepository airportRepo;

    //CRUD Operations
    public Airports addAirport(Airports airports) {
        return airportRepo.save(airports);
    }

    public List<Airports> getAllAirports() {
        return airportRepo.findAll();
    }

    public Airports getAirportById(int id) {
        return airportRepo.getOne(id);
    }

    public void updateAirport(Airports airports, int id) {
        airportRepo.save(airports);
    }

    public void deleteAirports(int id) {
        airportRepo.deleteById(id);
    }


    //Custom Operations
    public List<Airports> getAirportByName(String name) {

        return airportRepo.findByName(name);
    }

    public List<Airports> getAirportByCity(String city) {

        return airportRepo.findByCity(city);
    }

    public List<Airports> getAirportByAirlineId(int airlineId){
        return airportRepo.findByAirlineId(airlineId);
    }

}
