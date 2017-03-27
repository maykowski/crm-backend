package com.protoslab;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Wojtek on 09.03.2017.
 */

@Entity
public class Followup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    @OneToOne
    @JoinColumn (name = "status_id")
    private Status status;
    @Column(name="create_date")
    private Timestamp createDate;
    @Column(name="update_date")
    private Timestamp updateDate;
    @Column(name="due_date")
    private Timestamp dueDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    @JsonIgnore
    private Contact contact;


    public Followup() {
    }

    public Followup(String description, Status status, Timestamp dueDate, Contact contact) {
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Followpup[id=%d, status='%s', due='%s']",
                id, status, dueDate);
    }
}
