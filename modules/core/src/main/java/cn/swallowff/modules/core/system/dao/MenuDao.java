package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @description
 * @create 2019/6/29
 */
@Repository
@Mapper
public interface MenuDao extends CrudDao<Menu> {
}
