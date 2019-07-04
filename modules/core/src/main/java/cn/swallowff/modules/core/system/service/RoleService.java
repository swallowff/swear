package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.RoleDao;
import cn.swallowff.modules.core.system.entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends CrudService<RoleDao, Role> {

}
