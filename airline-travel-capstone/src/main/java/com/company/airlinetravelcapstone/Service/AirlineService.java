package com.company.airlinetravelcapstone.Service;

import com.company.airlinetravelcapstone.DAO.AirlineRepository;
import com.company.airlinetravelcapstone.DTO.Airlines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepo;

    //CRUD Operations
    public Airlines addAirline(Airlines airlines) {
        return airlineRepo.save(airlines);
    }

    public List<Airlines> getAllAirlines() {
        return airlineRepo.findAll();
    }

    public Airlines getAirlineById(int id) {
        return airlineRepo.getOne(id);
    }

    public void updateAirline(Airlines airlines, int id) {
        airlineRepo.save(airlines);
    }

    public void deleteAirlines(int id) {
        airlineRepo.deleteById(id);
    }

    //Custom Operation
    public List<Airlines> getAirlineByName(String name) {

        return airlineRepo.findByName(name);
    }
}
