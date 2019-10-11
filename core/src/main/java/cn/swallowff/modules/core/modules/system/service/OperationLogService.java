package cn.swallowff.modules.core.modules.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.modules.system.dao.OperationLogDao;
import cn.swallowff.modules.core.modules.system.entity.OperationLog;
import org.springframework.stereotype.Service;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Service
public class OperationLogService extends CrudService<OperationLogDao, OperationLog> {
}
