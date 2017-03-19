package com.protoslab;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Wojtek on 09.03.2017.
 */

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date when;
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
//    @Column (nullable = true)
//    private List<Followup> contacts;


    public Training() {
    }

    public Training(Date date) {
        this.when = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

//    public List<Followup> getContacts() {
//        return contacts;
//    }
//
//    public void setContacts(List<Followup> contacts) {
//        this.contacts = contacts;
//    }
}
