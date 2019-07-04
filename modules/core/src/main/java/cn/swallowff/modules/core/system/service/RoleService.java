package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.RoleDao;
import cn.swallowff.modules.core.system.dto.DeptRoleDto;
import cn.swallowff.modules.core.system.entity.Dept;
import cn.swallowff.modules.core.system.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService extends CrudService<RoleDao, Role> {

}
