package com.example.springbocs.model.entity;

import com.example.springbocs.model.type.ActivityType;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

// Getters and Setters explicitly initialized because using lombok @Data, @Getter, and @Setter do not work
//@Data
//@Getter
//@Setter
@Entity
@Table(name = "Activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity")
    private ActivityType activity; //to be changed to enum

    @Column(name = "performed_on")
    private Timestamp performedOn;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private LostItem lostItem;

    //getters
    public UUID getId() {
        return id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public ActivityType getActivityType() {
        return activity;
    }
    public Timestamp getPerformedOn() {
        return performedOn;
    }
    public Person getPerson() {
        return person;
    }
    public LostItem getLostItem() {
        return lostItem;
    }

    //Setters
    public void setId(UUID id) {
        this.id = id;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setActivityType(ActivityType activity) {
        this.activity = activity;
    }
    public void setPerformedOn(Timestamp performedOn) {
        this.performedOn = performedOn;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public void setLostItem(LostItem lostItem) {
        this.lostItem = lostItem;
    }
}
