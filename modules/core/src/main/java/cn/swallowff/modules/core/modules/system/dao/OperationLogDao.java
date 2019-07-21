package cn.swallowff.modules.core.modules.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.modules.system.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Repository
@Mapper
public interface OperationLogDao extends CrudDao<OperationLog> {
}
