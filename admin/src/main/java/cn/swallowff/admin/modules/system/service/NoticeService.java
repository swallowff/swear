package cn.swallowff.admin.modules.system.service;

import cn.swallowff.admin.cmomon.service.CrudService;
import cn.swallowff.admin.modules.system.dao.NoticeDao;
import cn.swallowff.admin.modules.system.entity.Notice;
import org.springframework.stereotype.Service;

/**
 * @author shenyu
 * @create 2019/4/12
 */
@Service
public class NoticeService extends CrudService<NoticeDao, Notice> {
}
