package com.example.springbocs.serviceTest;

import com.example.springbocs.repository.ActivityRepo;
import com.example.springbocs.service.ActivityService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class ActivityServiceTest {

    @InjectMocks
    private ActivityService activityService;

    @Mock
    private ActivityRepo activityRepo;

//    @Test
//    public void test
}
