package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.cmomon.dao.TreeDao;
import cn.swallowff.modules.core.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleDao extends TreeDao<Role> {

}
