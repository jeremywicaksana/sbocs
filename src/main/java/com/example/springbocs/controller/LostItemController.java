package com.example.springbocs.controller;

import com.example.springbocs.exception.NegativeLostItemException;
import com.example.springbocs.helper.ReadFile;
import com.example.springbocs.mapper.LostItemMapper;
import com.example.springbocs.model.dto.LostItemDto;
import com.example.springbocs.model.entity.Activity;
import com.example.springbocs.model.entity.LostItem;
import com.example.springbocs.model.entity.Person;
import com.example.springbocs.model.type.ActivityType;
import com.example.springbocs.service.ActivityService;
import com.example.springbocs.service.LostItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    @Operation(summary="return all of lost items",
            description = "this endpoint is returning all of the lost items within the lost_item table")
    public List<LostItemDto> getAllItems() {
        return lostItemService.getAllItems().stream()
                .map(lostItemMapper::lostItemToLostItemDto)
                .collect(Collectors.toList());
    }

    /**
     *  This endpoint will add the item into the lost item entry. If the item already exist it will
     *  increase the quantity of the item. Otherwise, it will add a new record. based on the file being introduced
     *
     *  In addition, this endpoint will add history record on the transaction of the lost item
     *
      * @param fileName name of the file
     * @return
     */
    @PostMapping("/add")
    @Operation(summary="adding lost item entry",
            description = "the filename is located in entry directory of main/java of the source code. At the moment," +
                    "only PDF conversion is supported")
    public void addItemEntry(@RequestParam String fileName) throws IOException {
        ReadFile readFile = new ReadFile();
        List<LostItemDto> entryDtoList = readFile.processFile(fileName);

        for (LostItemDto entryDto : entryDtoList) {
            LostItem entry = lostItemMapper.lostItemDtoToLostItem(entryDto);
            LostItem fetchedItem = lostItemService.getLostItem(entry.getName(), entry.getPlace());

            if (fetchedItem == null) {
                UUID rndmUUID = UUID.randomUUID();
                entry.setId(rndmUUID); //set a random UUID
                lostItemService.saveLostItem(entry);
            } else { //if record already exist, add the quantity of the record
                lostItemService.increaseItemQuantity(fetchedItem.getId(), entry.getQuantity());
            }
        }
    }

    /**
     * this endpoint is used if a user is claiming the lost item then the item quantity in the entry
     * will be decreased.
     *
     * In addition, it will add the history to activity table
     * @param claimedItem lostItemDto object with id being optional
     *
     */
    @PostMapping("/claim")
    @Operation(summary="User claim a lost item",
            description = "This endpoint will reduce the quantity of the lost item while adding a footprint under activity table" +
                    "User table is created, but there is only a mock table where it has a hardcoded users with userId" +
                    "1,2, and 3. It is advised to only use userId that exist in the table to claim the lost item")
    public void claimLostItem(@RequestBody LostItemDto claimedItem, @RequestParam Integer personId) {
        LostItem entry = lostItemMapper.lostItemDtoToLostItem(claimedItem);
        LostItem sourceItem = lostItemService.getLostItem(entry.getName(), entry.getPlace());

        Integer entryAmount = entry.getQuantity();
        Integer sourceAmount = sourceItem.getQuantity();

        if (entryAmount > sourceAmount) {
            throw new NegativeLostItemException("Claiming item can't be higher than item amount");
        } else { //if record already exist, add the quantity of the record
            lostItemService.reduceItemQuantity(sourceItem.getId(), entry.getQuantity());
        }

        //add the activity service while adding/updating record
        Activity activity = new Activity();
        Person person = new Person();
        LostItem lostItem = new LostItem();

        //person + lost item
        person.setId(personId);
        lostItem.setId(sourceItem.getId());

        //additional info for activity
        LocalDateTime curTime = LocalDateTime.now();
        UUID rndmUUID = UUID.randomUUID();

        //set all activity variables
        activity.setId(rndmUUID);
        activity.setActivityType(ActivityType.CLAIMED);
        activity.setPerformedOn(Timestamp.valueOf(curTime));
        activity.setQuantity(entryAmount);
        activity.setLostItem(lostItem);
        activity.setPerson(person);

        //save the activity record
        activityService.addActivity(activity);
    }
}
