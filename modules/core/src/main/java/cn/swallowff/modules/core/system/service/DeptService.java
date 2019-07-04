package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.TreeService;
import cn.swallowff.modules.core.system.dao.DeptDao;
import cn.swallowff.modules.core.system.entity.Dept;
import org.springframework.stereotype.Service;

@Service
public class DeptService extends TreeService<DeptDao, Dept> {

}
