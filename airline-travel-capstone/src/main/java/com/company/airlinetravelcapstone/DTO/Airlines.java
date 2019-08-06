package com.company.airlinetravelcapstone.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "airlines")
public class Airlines {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;


    @OneToMany(mappedBy="airlines", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<Airports> airports;


    @OneToMany(mappedBy="allAirlines", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<Flights> flights;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airlines airlines = (Airlines) o;
        return Objects.equals(getId(), airlines.getId()) &&
                Objects.equals(getName(), airlines.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Airlines{" +
                "airlineId=" + id +
                ", airlineName='" + name + '\'' +
                '}';
    }
}
