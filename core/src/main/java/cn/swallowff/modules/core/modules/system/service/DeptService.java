package cn.swallowff.modules.core.modules.system.service;

import cn.swallowff.modules.core.cmomon.service.TreeService;
import cn.swallowff.modules.core.modules.system.dao.DeptDao;
import cn.swallowff.modules.core.modules.system.dto.DtreeNode;
import cn.swallowff.modules.core.modules.system.entity.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService extends TreeService<DeptDao, Dept> {

}
