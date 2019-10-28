package cn.swallowff.admin.modules.system.dao;

import cn.swallowff.admin.modules.system.dto.DtreeNode;
import cn.swallowff.admin.modules.system.entity.Menu;
import cn.swallowff.admin.cmomon.dao.TreeDao;
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
