package com.example.springbocs.controller;

import com.example.springbocs.exception.NegativeLostItemException;
import com.example.springbocs.mapper.LostItemMapper;
import com.example.springbocs.model.dto.LostItemDto;
import com.example.springbocs.model.entity.Activity;
import com.example.springbocs.model.entity.LostItem;
import com.example.springbocs.model.type.ActivityType;
import com.example.springbocs.service.ActivityService;
import com.example.springbocs.service.LostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/lost-item")
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    @Autowired
    private ActivityService activityService;

    private LostItemMapper lostItemMapper = LostItemMapper.INSTANCE;

    /**
     * it will return all the lost items including the one that is currently not available anymore
     * @return list of lostitemdto
     */
    @GetMapping("/all")
    public List<LostItemDto> getAllItems() {
        return lostItemService.getAllItems().stream()
                .map(lostItemMapper::lostItemToLostItemDto)
                .collect(Collectors.toList());
    }

    /**
     *  This endpoint will add the item into the lost item entry. If the item already exist it will
     *  increase the quantity of the item. Otherwise, it will add a new record.
     *
     *  In addition, this endpoint will add history record on the transaction of the lost item
     *
      * @param entryDto lostItemDto object with id being optional
     * @return
     */
    @PostMapping("/add")
    public LostItem addItemEntry(@RequestBody LostItemDto entryDto) {
        LostItem entry = lostItemMapper.lostItemDtoToLostItem(entryDto);
        LostItem fetchedItem = lostItemService.getLostItem(entry.getName(), entry.getPlace());
//        ActivityType activity;
        if (fetchedItem == null) {
            UUID rndmUUID = UUID.randomUUID();
            entry.setId(rndmUUID); //set a random UUID
            lostItemService.saveLostItem(entry);
        } else { //if record already exist, add the quantity of the record
            lostItemService.increaseItemQuantity(fetchedItem.getId(), fetchedItem.getQuantity());
        }

        //add the activity service while adding/updating record
        ActivityService activityService = new ActivityService();
//        Activity

        return entry;
    }

    /**
     * this endpoint is used if a user is claiming the lost item then the item quantity in the entry
     * will be decreased.
     *
     * In addition, it will add the history to activity table
     * @param entryDto lostItemDto object with id being optional
     * @return
     */
    @PostMapping("/claim")
    public void claimLostItem(@RequestBody LostItemDto entryDto, @RequestParam Integer personId) {
        LostItem entry = lostItemMapper.lostItemDtoToLostItem(entryDto);
        LostItem fetchedItem = lostItemService.getLostItem(entry.getName(), entry.getPlace());

        Integer entryAmount = entry.getQuantity();
        Integer claimAmount = fetchedItem.getQuantity();

        if (entryAmount < claimAmount) {
            throw new NegativeLostItemException("Claiming item can't be higher than item amount");
        } else { //if record already exist, add the quantity of the record
            lostItemService.reduceItemQuantity(fetchedItem.getId(), fetchedItem.getQuantity());
        }

        //add the activity service while adding/updating record
        Activity activity = new Activity();

        //additional info for activity
        LocalDateTime curTime = LocalDateTime.now();
        UUID rndmUUID = UUID.randomUUID();

        //set all activity variables
//        activity.setId(rndmUUID);
//        activity.setActivityType(ActivityType.CLAIMED);
//        activity.setPerformedOn(Timestamp.valueOf(curTime));
//        activity.setQuantity(claimAmount);
//        activity.setItemId(fetchedItem.getId());
//        activity.setPersonId(personId);

        //save the activity record
        activityService.addActivity(activity);
    }
}
