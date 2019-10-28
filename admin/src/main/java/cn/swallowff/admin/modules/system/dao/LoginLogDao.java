package cn.swallowff.admin.modules.system.dao;

import cn.swallowff.admin.modules.system.entity.LoginLog;
import cn.swallowff.admin.cmomon.dao.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Repository
@Mapper
public interface LoginLogDao extends CrudDao<LoginLog> {
}
