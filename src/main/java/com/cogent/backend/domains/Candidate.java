package com.cogent.backend.domains;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Candidate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String city;

    @Column(name = "election_type")
    private String electionType;
    private String imageUrl;
    private String state;
    private long votes;
    private String password;
    private Date date;
    @Column(name = "email_id")
    private String eMailid;
    @Column(name = "contact_no")
    private String contactno;

    public Candidate(){}

    public Candidate(String name, String city, String electionType,
                     String eMailId, String contactno, String state, String password, Date date){
        this.city = city;
        this.electionType = electionType;
        this.eMailid = eMailId;
        this.name = name;
        this.date = date;
        this.contactno = contactno;
        this.state = state;
        this.password = password;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }



    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getElectionType() {
        return electionType;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String geteMailid() {
        return eMailid;
    }

    public void seteMailid(String eMailid) {
        this.eMailid = eMailid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                votes == candidate.votes &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(city, candidate.city) &&
                Objects.equals(electionType, candidate.electionType) &&
                Objects.equals(imageUrl, candidate.imageUrl) &&
                Objects.equals(state, candidate.state) &&
                Objects.equals(password, candidate.password) &&
                Objects.equals(date, candidate.date) &&
                Objects.equals(eMailid, candidate.eMailid) &&
                Objects.equals(contactno, candidate.contactno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, electionType, imageUrl, state, votes, password, date, eMailid, contactno);
    }
}
