package cn.swallowff.admin.modules.system.dao;

import cn.swallowff.admin.modules.system.entity.Notice;
import cn.swallowff.admin.cmomon.dao.CrudDao;
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
