package cn.swallowff.admin.modules.system.service;

import cn.swallowff.admin.cmomon.service.TreeService;
import cn.swallowff.admin.modules.system.dao.DeptDao;
import cn.swallowff.admin.modules.system.entity.Dept;
import org.springframework.stereotype.Service;

@Service
public class DeptService extends TreeService<DeptDao, Dept> {

}
