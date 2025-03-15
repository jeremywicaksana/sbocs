package com.example.springbocs.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

// Getters and Setters explicitly initialized because using lombok @Data, @Getter, and @Setter do not work
@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "lost_item")
public class LostItem {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "place")
    private String place;

    @OneToMany (mappedBy = "lostItem")
    private List<Activity> activities;

    //getters
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public String getPlace() {
        return place;
    }

    //Setters
    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setPlace(String place) {
        this.place = place;
    }
}
