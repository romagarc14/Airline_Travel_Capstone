package com.company.airlinetravelcapstone.DTO;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="flights")
public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Length(min=3, max=3)
    private String airportName;
    @NotNull
    private Integer numOfStops;
    @NotNull
    private Integer minutesTraveled;
    @NotNull
    private Integer distance;
    @NotNull
    private String destination;
    private Double price;
    private Integer airlineId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airlineId", updatable = false, insertable = false)
    private Airlines allAirlines;

    public Airlines getAllAirlines() {
        return allAirlines;
    }

    public void setAllAirlines(Airlines allAirlines) {
        this.allAirlines = allAirlines;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Integer getNumOfStops() {
        return numOfStops;
    }

    public void setNumOfStops(Integer numOfStops) {
        this.numOfStops = numOfStops;
    }

    public Integer getMinutesTraveled() {
        return minutesTraveled;
    }

    public void setMinutesTraveled(Integer minutesTraveled) {
        this.minutesTraveled = minutesTraveled;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        Flights flights = (Flights) o;
        return Objects.equals(getId(), flights.getId()) &&
                Objects.equals(getAirportName(), flights.getAirportName()) &&
                Objects.equals(getNumOfStops(), flights.getNumOfStops()) &&
                Objects.equals(getMinutesTraveled(), flights.getMinutesTraveled()) &&
                Objects.equals(getDistance(), flights.getDistance()) &&
                Objects.equals(getDestination(), flights.getDestination()) &&
                Objects.equals(getAllAirlines(), flights.getAllAirlines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAirportName(), getNumOfStops(), getMinutesTraveled(), getDistance(), getDestination(), getAllAirlines());
    }

    @Override
    public String toString() {
        return "Flights{" +
                "id=" + id +
                ", airportName='" + airportName + '\'' +
                ", numOfStops=" + numOfStops +
                ", minutesTraveled=" + minutesTraveled +
                ", distance=" + distance +
                ", destination='" + destination + '\'' +
                ", allAirlines=" + allAirlines +
                '}';
    }
}
