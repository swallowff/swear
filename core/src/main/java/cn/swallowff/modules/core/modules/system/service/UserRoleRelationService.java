package cn.swallowff.modules.core.modules.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.modules.system.dao.UserRoleRelationDao;
import cn.swallowff.modules.core.modules.system.entity.UserRoleRelation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/7
 */
@Service
public class UserRoleRelationService extends CrudService<UserRoleRelationDao, UserRoleRelation> {
    public List<UserRoleRelation> findByUserId(String userId) {
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setUserId(userId);
        return super.findList(userRoleRelation);
    }

    public int delByUserId(String uid) {
        return crudDao.delByUserId(uid);
    }
}
