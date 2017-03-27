package com.protoslab;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Wojtek on 09.03.2017.
 */

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String lid;
    private String name;
    private String title;
    private String location;
    private String phone;
    private String email;
    private String company;
    private String link;
    @Column(name = "phone_summary")
    private String phoneSummary;
    @Lob
    private String summary;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "update_date")
    private Timestamp updateDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
    private List<Followup> followups;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "training_id")
    private Training training;
    @Transient
    private Date trainingDate;
    @Transient
    private Date lastFollowupDate;
    @Transient
    private String lastFollowupStatus;

    public Contact() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPhoneSummary() {
        return phoneSummary;
    }

    public void setPhoneSummary(String phoneSummary) {
        this.phoneSummary = phoneSummary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public List<Followup> getFollowups() {
        return this.followups;
    }

    public void setFollowups(List<Followup> followups) {
        this.followups = followups;
    }


    public Training getTraining() {
        return training;
    }

    public Date getTrainingDate() {
        if (training == null) return null;
        return training.getWhen();
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Date getLastFollowupDate() {
        if (this.followups.isEmpty())
            return null;
        List<Followup> followups = new ArrayList<>(this.followups);
        Collections.sort(followups, Comparator.comparing(Followup::getCreateDate).reversed());
        return followups.get(0).getDueDate();
    }

    public void setLastFollowupDate(Date lastFollowupDate) {
        this.lastFollowupDate = lastFollowupDate;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getLastFollowupStatus() {
        if (this.followups.isEmpty())
            return null;
        List<Followup> followups = new ArrayList<>(this.followups);
        Collections.sort(followups, Comparator.comparing(Followup::getCreateDate).reversed());
        return followups.get(0).getStatus() != null?followups.get(0).getStatus().getName():null;
    }

    public void setLastFollowupStatus(String lastFollowupStatus) {
        this.lastFollowupStatus = lastFollowupStatus;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, name='%s', company='%s']",
                id, name, company);
    }
}
