package com.example.springbocs.repository;

import com.example.springbocs.model.entity.LostItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LostItemRepo extends JpaRepository<LostItem, UUID> {

    /**
     * Query to remove the quantity of item after being claimed and ensuring quantity will never
     * reach negative
     * @param id the UUID of the item
     * @param quantity amount of the item to be claimed
     * @Constraint item quantity within lostItem table > 0
     */
    @Modifying
    @Transactional
    @Query("UPDATE LostItem l " +
            "SET l.quantity = l.quantity - :quantity " +
            "WHERE l.id = :id and l.quantity >= :quantity")
    void reduceQuantity(UUID id, Integer quantity);

    /**
     * Updating the amount of quantity based
     * @param id UUID of item
     * @param quantity amount of the item to be added
     */
    @Modifying
    @Transactional
    @Query("UPDATE LostItem l " +
            "SET l.quantity = l.quantity + :quantity " +
            "WHERE l.id = :id")
    void increaseQuantity(UUID id, Integer quantity);

    /**
     * return the row of the lost item based on its name and place. No lost item entry should have
     * duplicate name and place. This function is mainly to do a check before performing transactional
     * activity
     * @param name name of the lost item
     * @param place location on where the item is lost
     * @return only one record of the lost item
     */
    LostItem findByNameAndPlace(String name, String place);
}
