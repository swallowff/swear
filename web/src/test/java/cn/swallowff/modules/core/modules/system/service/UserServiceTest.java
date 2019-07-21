package cn.swallowff.modules.core.modules.system.service;

import cn.swallowff.web.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void selectById() {
        userService.selectById("1145706884594966528");
    }

    @Test
    public void selectByAccount() {
    }


}