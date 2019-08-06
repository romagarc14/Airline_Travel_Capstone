package com.company.airlinetravelcapstone.DTO;

import com.company.airlinetravelcapstone.DTO.Airlines;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="airports")
public class Airports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Length(min=3, max=3)
    private String name;
    @NotNull
    private String city;
    private Integer airlineId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airlineId", updatable = false, insertable = false)
    private Airlines airlines;


    public Airlines getAirlines() {
        return airlines;
    }

    public void setAirlines(Airlines airlines) {
        this.airlines = airlines;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airports airports = (Airports) o;
        return Objects.equals(getId(), airports.getId()) &&
                Objects.equals(getName(), airports.getName()) &&
                Objects.equals(getCity(), airports.getCity()) &&
                Objects.equals(getAirlines(), airports.getAirlines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCity(), getAirlines());
    }

    @Override
    public String toString() {
        return "Airports{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", airlines=" + airlines +
                '}';
    }
}
