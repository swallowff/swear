package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Mapper
public interface DeptDao extends CrudDao<Dept> {
}
