package com.example.springbocs.repository;

import com.example.springbocs.model.entity.Activity;
import com.example.springbocs.model.type.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, UUID> {

    @Query("SELECT a " +
            "FROM Activity a " +
            "JOIN a.person p " +
            "JOIN a.lostItem l " +
            "WHERE a.activity = :activity")
    List<Activity> findByActivity(ActivityType activity);
}
