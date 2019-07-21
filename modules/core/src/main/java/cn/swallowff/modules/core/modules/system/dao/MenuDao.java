package cn.swallowff.modules.core.modules.system.dao;

import cn.swallowff.modules.core.cmomon.dao.TreeDao;
import cn.swallowff.modules.core.modules.system.dto.DtreeNode;
import cn.swallowff.modules.core.modules.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @description
 * @create 2019/6/29
 */
@Repository
@Mapper
public interface MenuDao extends TreeDao<Menu> {
    List<DtreeNode> findMenuListWithRole(@Param(value = "menu") Menu menu, @Param(value = "roleId") String roleId);

}
