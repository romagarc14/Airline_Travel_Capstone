package com.company.airlinetravelcapstone.DAO;

import com.company.airlinetravelcapstone.DTO.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airlines, Integer>{
    List<Airlines> findByName(String name);

}
