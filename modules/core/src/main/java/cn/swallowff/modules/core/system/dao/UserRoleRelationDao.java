package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.entity.UserRoleRelation;
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
