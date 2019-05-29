package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.OperationLogDao;
import cn.swallowff.modules.core.system.entity.OperationLog;
import org.springframework.stereotype.Service;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Service
public class OperationLogService extends CrudService<OperationLogDao,OperationLog> {
}
