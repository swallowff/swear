package cn.swallowff.modules.core.system.service;

import cn.swallowff.common.collect.ListUtils;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.UserDao;
import cn.swallowff.modules.core.system.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends CrudService<UserDao, User> {

    public PageResp<User> findPage(User user){
        PageHelper.startPage(user.getPage(),user.getLimit());
        List<User> list = super.findList(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageResp(pageInfo);
    }

    public User selectByAccount(String account){
        User user = new User();
        user.setAccount(account);
        List<User> results = super.findList(user);
        return ListUtils.isEmpty(results) ? null : results.get(0);
    }



}
