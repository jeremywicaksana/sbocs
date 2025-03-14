package com.example.springbocs.service;

import com.example.springbocs.model.entity.LostItem;
import com.example.springbocs.repository.LostItemRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LostItemService {

    @Autowired
    LostItemRepo lostItemRepo;

    /**
     *
     * @return all lost item entry
     */
    public List<LostItem> getAllItems() {
        return lostItemRepo.findAll();
    }

    /**
     * Get a certain lost item based on the item name and location
     * @param item name of the item
     * @param place location where it was found
     * @return a lostItem object with its quantity and id
     */
    public LostItem getLostItem(String item, String place) {
        return lostItemRepo.findByNameAndPlace(item, place);
    }

    /**
     * add new record based on the entity of the lost item
     * @param lostItem the entity of lost item
     *
     */
    @Transactional
    public void saveLostItem(LostItem lostItem) {
        lostItemRepo.save(lostItem);
    }

    /**
     * reduce the amount of item if an item is claimed
     * @param id UUID of the item
     * @param quantity amount of the item to be claimed
     */
    @Transactional
    public void reduceItemQuantity(UUID id, int quantity) {
        lostItemRepo.reduceQuantity(id, quantity);
    }

    /**
     * increase the amount of item
     * @param id UUID of the item
     * @param quantity amount of item to be added
     */
    @Transactional
    public void increaseItemQuantity(UUID id, int quantity) {
        lostItemRepo.increaseQuantity(id, quantity);
    }

}
