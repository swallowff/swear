package cn.swallowff.admin.modules.system.service;

import cn.swallowff.admin.cmomon.service.CrudService;
import cn.swallowff.admin.modules.system.dao.LoginLogDao;
import cn.swallowff.admin.modules.system.entity.LoginLog;
import org.springframework.stereotype.Service;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Service
public class LoginLogService extends CrudService<LoginLogDao, LoginLog> {
}
