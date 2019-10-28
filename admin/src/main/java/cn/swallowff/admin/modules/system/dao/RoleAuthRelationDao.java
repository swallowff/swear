package cn.swallowff.admin.modules.system.dao;

import cn.swallowff.admin.modules.system.entity.RoleAuthRelation;
import cn.swallowff.admin.cmomon.dao.CrudDao;
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
