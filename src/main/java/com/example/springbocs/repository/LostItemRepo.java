package com.example.springbocs.repository;

import com.example.springbocs.model.entity.LostItem;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import java.util.UUID;

@Repository
public interface LostItemRepo extends JpaRepository<LostItem, UUID> {

    /**
     * Query to remove the quantity of item after being claimed and ensuring quantity will never
     * reach negative
     * @param id the UUID of the item
     * @param claimQuantity amount of the item to be claimed
     * @Constraint item quantity within lostItem table > 0
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE LostItem as lost SET lost.quantity = lost.quantity - :claimQuantity WHERE lost.id = :id and lost.quantity >= :claimQuantity")
    void reduceQuantity(@Param("id") UUID id, @Param("claimQuantity") Integer claimQuantity);

    /**
     * Updating the amount of quantity based
     * @param id UUID of item
     * @param addQuantity amount of the item to be added
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE LostItem as lost SET lost.quantity = lost.quantity + :addQuantity WHERE lost.id = :id")
    void increaseQuantity(@Param("id") UUID id, @Param("addQuantity") Integer addQuantity);

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
