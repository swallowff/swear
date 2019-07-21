package cn.swallowff.modules.core.modules.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.modules.system.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author shenyu
 * @create 2019/4/12
 */
@Repository
@Mapper
public interface NoticeDao extends CrudDao<Notice> {

}
