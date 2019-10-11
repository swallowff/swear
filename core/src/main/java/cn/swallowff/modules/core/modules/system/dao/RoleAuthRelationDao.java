package cn.swallowff.modules.core.modules.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.modules.system.entity.RoleAuthRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/7
 */
@Repository
@Mapper
public interface RoleAuthRelationDao extends CrudDao<RoleAuthRelation> {

    int delByRoleId(String roleId);

    List<String> findPermissionsByRole(String roleId);
}
