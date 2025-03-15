package com.example.springbocs.serviceTest;

import com.example.springbocs.model.entity.LostItem;
import com.example.springbocs.repository.LostItemRepo;
import com.example.springbocs.service.LostItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LostItemServiceTest {

    @InjectMocks
    private LostItemService lostItemService;

    @Mock
    private LostItemRepo lostItemRepo;

    //param
    private LostItem lostItem;
    private LostItem lostItemSave;
    private List<LostItem> lostItemList = new ArrayList<>();
    private UUID lostItemId;


    @BeforeEach
    void setup() {
        UUID rndmUUIDLostItem = UUID.randomUUID();
        UUID rndmUUIDLostItem2 = UUID.randomUUID();
        lostItemId = rndmUUIDLostItem;// the saved mock
        LostItem lostItemMockSave = new LostItem();
        LostItem lostItemMock1 = new LostItem();

        //init mock save
        lostItemMockSave.setId(rndmUUIDLostItem);
        lostItemMockSave.setQuantity(10);
        lostItemMockSave.setName("util");
        lostItemMockSave.setPlace("school");

        //init other mock
        lostItemMock1.setId(rndmUUIDLostItem2);
        lostItemMock1.setQuantity(5);
        lostItemMock1.setName("grocery");
        lostItemMock1.setPlace("plaza");

        lostItemSave = lostItemMockSave;

        lostItemList.add(lostItemMock1);
        lostItemList.add(lostItemMockSave);

    }

    @Test
    public void testGetAllItems() {
        when(lostItemRepo.findAll()).thenReturn(lostItemList);

        List<LostItem> allItems = lostItemService.getAllItems();

        assertNotNull(allItems);
        assertEquals(lostItemList.size(), allItems.size());
    }

    @Test
    public void testGetLostItem() {
        when(lostItemRepo.findByNameAndPlace("util", "school")).thenReturn(lostItemSave);

        LostItem lostItem = lostItemService.getLostItem("util", "school");

        assertNotNull(lostItem);
        assertEquals(lostItemSave.getName(), lostItem.getName());
        assertEquals(lostItemSave.getPlace(), lostItem.getPlace());
        assertEquals(lostItemSave.getQuantity(), lostItem.getQuantity());
    }

    @Test
    public void testSaveLostItem() {
        lostItemService.saveLostItem(lostItemSave);
        verify(lostItemRepo).save(lostItemSave);
    }

    @Test
    public void testReduceQuantity() {
    }

    @Test
    public void testIncreaseQuantity() {
    }
}
