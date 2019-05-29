package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.NoticeDao;
import cn.swallowff.modules.core.system.entity.Notice;
import org.springframework.stereotype.Service;

/**
 * @author shenyu
 * @create 2019/4/12
 */
@Service
public class NoticeService extends CrudService<NoticeDao, Notice> {
}
