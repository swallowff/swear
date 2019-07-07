package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.TreeService;
import cn.swallowff.modules.core.system.dao.RoleDao;
import cn.swallowff.modules.core.system.dto.DtreeNode;
import cn.swallowff.modules.core.system.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends TreeService<RoleDao, Role> {

    public List<DtreeNode> findDtreeNodeListWithUid(Role role, String userId) {
        return crudDao.findDtreeNodeListWithUid(role,userId);
    }
}
