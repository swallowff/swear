package cn.swallowff.admin.modules.system.dao;

import cn.swallowff.admin.modules.system.dto.DtreeNode;
import cn.swallowff.admin.modules.system.entity.Role;
import cn.swallowff.admin.cmomon.dao.TreeDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleDao extends TreeDao<Role> {

    List<DtreeNode> findDtreeNodeListWithUid(@Param(value = "role") Role role, @Param(value = "userId") String userId);
}
