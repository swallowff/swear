package cn.swallowff.admin.modules.cs.dao;

import cn.swallowff.admin.cmomon.dao.CrudDao;
import cn.swallowff.admin.modules.cs.entity.CsUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Repository
@Mapper
public interface CsUserDao extends CrudDao<CsUser> {

}