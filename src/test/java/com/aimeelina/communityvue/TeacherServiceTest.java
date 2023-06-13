package com.aimeelina.communityvue;

import com.aimeelina.communityvue.service.TeacherService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityVueApplication.class)
public class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;
}
