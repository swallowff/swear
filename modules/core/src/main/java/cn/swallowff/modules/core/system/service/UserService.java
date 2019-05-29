package cn.swallowff.modules.core.system.service;

import cn.swallowff.common.collect.ListUtils;
import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.UserDao;
import cn.swallowff.modules.core.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends CrudService<UserDao, User> {

    public User selectById(String id){
        return super.selectById(id);
    }

    public User selectByAccount(String account){
        User user = new User();
        user.setAccount(account);
        List<User> results = super.findList(user);
        return ListUtils.isEmpty(results) ? null : results.get(0);
    }


}
