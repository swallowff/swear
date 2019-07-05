package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.TreeService;
import cn.swallowff.modules.core.system.dao.RoleDao;
import cn.swallowff.modules.core.system.entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends TreeService<RoleDao, Role> {

}
