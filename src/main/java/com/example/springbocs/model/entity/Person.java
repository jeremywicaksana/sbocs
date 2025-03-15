package com.example.springbocs.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// Getters and Setters explicitly initialized because using lombok @Data, @Getter, and @Setter do not work
@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "person")
    private List<Activity> acvtivies;

    //getters
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
