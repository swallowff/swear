package cn.swallowff.admin.modules.system.dao;

import cn.swallowff.admin.modules.system.entity.User;
import cn.swallowff.admin.cmomon.dao.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao extends CrudDao<User> {
}
