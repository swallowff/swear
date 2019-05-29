package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends CrudDao<User> {
}
