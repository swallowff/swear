package cn.swallowff.admin.modules.system.service;

import cn.swallowff.admin.cmomon.service.CrudService;
import cn.swallowff.admin.modules.system.dao.RoleAuthRelationDao;
import cn.swallowff.admin.modules.system.entity.RoleAuthRelation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/7
 */
@Service
public class RoleAuthRelationService extends CrudService<RoleAuthRelationDao, RoleAuthRelation> {

    public RoleAuthRelation selectByRoleIdAndMenuId(String roleId, String menuId) {
        RoleAuthRelation roleAuthRelation = new RoleAuthRelation();
        roleAuthRelation.setRoleId(roleId);
        roleAuthRelation.setMenuId(menuId);
        return super.findEntity(roleAuthRelation);
    }

    @Transactional
    public int delByRoleId(String roleId) {
        return crudDao.delByRoleId(roleId);
    }

    public List<String> findPermissionsByRole(String roleId) {
        return crudDao.findPermissionsByRole(roleId);
    }
}
