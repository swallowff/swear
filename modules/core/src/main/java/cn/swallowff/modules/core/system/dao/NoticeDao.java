package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shenyu
 * @create 2019/4/12
 */
@Mapper
public interface NoticeDao extends CrudDao<Notice> {

}
