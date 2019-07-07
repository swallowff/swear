package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.RoleAuthRelationDao;
import cn.swallowff.modules.core.system.entity.RoleAuthRelation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/7
 */
@Service
public class RoleAuthRelationService extends CrudService<RoleAuthRelationDao,RoleAuthRelation> {

    public RoleAuthRelation selectByRoleIdAndMenuId(String roleId, String menuId) {
        RoleAuthRelation roleAuthRelation = new RoleAuthRelation();
        roleAuthRelation.setRoleId(roleId);
        roleAuthRelation.setMenuId(menuId);
        List<RoleAuthRelation> list = crudDao.findList(roleAuthRelation);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }


    public int delByRoleId(String roleId) {
        return crudDao.delByRoleId(roleId);
    }
}
