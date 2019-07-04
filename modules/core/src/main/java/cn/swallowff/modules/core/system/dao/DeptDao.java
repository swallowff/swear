package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.dto.DeptRoleDto;
import cn.swallowff.modules.core.system.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Repository
@Mapper
public interface DeptDao extends CrudDao<Dept> {
}
