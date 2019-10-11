package cn.swallowff.modules.core.modules.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.modules.system.dao.UserDao;
import cn.swallowff.modules.core.modules.system.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<UserDao, User> {

    public User selectByAccount(String account) {
        User user = new User();
        user.setAccount(account);
        return super.findEntity(user);
    }


}
