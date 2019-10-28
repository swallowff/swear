package cn.swallowff.admin.modules.system.dao;

import cn.swallowff.admin.modules.system.entity.UserRoleRelation;
import cn.swallowff.admin.cmomon.dao.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author shenyu
 * @create 2019/7/7
 */
@Repository
@Mapper
public interface UserRoleRelationDao extends CrudDao<UserRoleRelation> {
    int delByUserId(String uid);
}
