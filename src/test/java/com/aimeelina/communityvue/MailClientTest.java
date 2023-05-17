package com.aimeelina.communityvue;

import com.aimeelina.communityvue.utils.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityVueApplication.class)
public class MailClientTest {
    @Autowired
    private MailClient mailClient;

    @Test
    public void testSend(){
        mailClient.sendMail("841213768@qq.com","SpringBoot mail test","test content");
    }
}
