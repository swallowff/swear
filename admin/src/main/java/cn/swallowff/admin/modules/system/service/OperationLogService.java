package cn.swallowff.admin.modules.system.service;

import cn.swallowff.admin.cmomon.service.CrudService;
import cn.swallowff.admin.modules.system.dao.OperationLogDao;
import cn.swallowff.admin.modules.system.entity.OperationLog;
import org.springframework.stereotype.Service;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Service
public class OperationLogService extends CrudService<OperationLogDao, OperationLog> {
}
