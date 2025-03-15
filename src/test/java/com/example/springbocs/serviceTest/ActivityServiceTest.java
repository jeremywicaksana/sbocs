package com.example.springbocs.serviceTest;

import com.example.springbocs.model.entity.Activity;
import com.example.springbocs.model.entity.LostItem;
import com.example.springbocs.model.entity.Person;
import com.example.springbocs.model.type.ActivityType;
import com.example.springbocs.repository.ActivityRepo;
import com.example.springbocs.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {

    @InjectMocks
    private ActivityService activityService;

    @Mock
    private ActivityRepo activityRepo;

    private List<Activity> activities = new ArrayList<>();
    private List<Activity> activitiesClaim = new ArrayList<>();
    private List<Activity> activitiesAdd = new ArrayList<>();
    private Activity activitySave = new Activity();

    @BeforeEach
    void setup() {
        //init mock
        LocalDateTime curTime = LocalDateTime.now();
        UUID rndmUUIDActivity = UUID.randomUUID();
        UUID rndmUUIDLostItem = UUID.randomUUID();

        //lostItem init
        LostItem lostItem = new LostItem();
        lostItem.setId(rndmUUIDLostItem);

        //person init
        Person person = new Person();
        person.setId(1);

        //activity init
        Activity mockActivitySave = new Activity();

        //for claim item
        for (int i = 1; i < 4; i++) {
            Activity mockActivity = new Activity();
            mockActivity.setActivityType(ActivityType.CLAIMED);
            mockActivity.setPerson(person);
            mockActivity.setLostItem(lostItem);
            mockActivity.setId(rndmUUIDActivity);
            mockActivity.setQuantity(i);
            mockActivity.setPerformedOn(Timestamp.valueOf(curTime));
            //add activities
            activities.add(mockActivity);
            activitiesClaim.add(mockActivity);
        }

        //for add item
        for (int i = 1; i < 3; i++) {
            Activity mockActivity = new Activity();
            mockActivity.setActivityType(ActivityType.ADDED);
            mockActivity.setPerson(person);
            mockActivity.setLostItem(lostItem);
            mockActivity.setId(rndmUUIDActivity);
            mockActivity.setQuantity(i);
            mockActivity.setPerformedOn(Timestamp.valueOf(curTime));
            //add activities
            activities.add(mockActivity);
            activitiesAdd.add(mockActivity);
        }

        //save mock
        activitySave.setActivityType(ActivityType.CLAIMED);
        activitySave.setPerson(person);
        activitySave.setLostItem(lostItem);
        activitySave.setId(rndmUUIDActivity);
        activitySave.setQuantity(3);
        activitySave.setPerformedOn(Timestamp.valueOf(curTime));
    }

    @Test
    public void testGetActivityById() {
        //mock
        when(activityRepo.findByActivity(ActivityType.CLAIMED)).thenReturn(activitiesClaim);
        when(activityRepo.findByActivity(ActivityType.ADDED)).thenReturn(activitiesAdd);

        //get result
        List<Activity> resClaim = activityService.getActivites(ActivityType.CLAIMED);
        List<Activity> resAdd = activityService.getActivites(ActivityType.ADDED);

        //assert
        assertNotNull(resClaim);
        assertNotNull(resAdd);
        assertEquals(activitiesClaim.size(), resClaim.size());
        assertEquals(activitiesAdd.size(), resAdd.size());
    }

    @Test
    public void testSaveActivity() {
        activityService.addActivity(activitySave);
        verify(activityRepo).save(activitySave);
    }

    @Test
    public void testGetAllActivities() {
        when(activityRepo.findAll()).thenReturn(activities);

        List<Activity> res = activityService.getAllActivities();

        //assert
        assertNotNull(res);
        assertEquals(activities.size(), res.size());
    }
}
