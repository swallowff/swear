package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.TreeDao;
import cn.swallowff.modules.core.system.dto.DtreeNode;
import cn.swallowff.modules.core.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleDao extends TreeDao<Role> {

    List<DtreeNode> findDtreeNodeListWithUid(@Param(value = "role") Role role, @Param(value = "userId") String userId);
}
