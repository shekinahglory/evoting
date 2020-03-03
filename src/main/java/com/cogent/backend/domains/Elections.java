package com.cogent.backend.domains;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Elections implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String electionType;
    private String city;
    private LocalDateTime start;
    private LocalDateTime end;

    @OneToMany(cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Candidate> candidate = new HashSet<>();

    public Elections(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElectionType() {
        return electionType;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Set<Candidate> getCandidate() {
        return candidate;
    }

    public void setCandidate(Set<Candidate> candidate) {
        this.candidate = candidate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elections elections = (Elections) o;
        return id == elections.id &&
                Objects.equals(electionType, elections.electionType) &&
                Objects.equals(city, elections.city) &&
                Objects.equals(start, elections.start) &&
                Objects.equals(end, elections.end) &&
                Objects.equals(candidate, elections.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, electionType, city, start, end, candidate);
    }
}
