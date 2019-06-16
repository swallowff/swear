package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.omg.CORBA.INTERNAL;

@Mapper
public interface UserDao extends CrudDao<User> {
    Integer test();
}
