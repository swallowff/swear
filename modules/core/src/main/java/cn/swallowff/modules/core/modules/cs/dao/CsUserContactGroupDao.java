package cn.swallowff.modules.core.modules.cs.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.modules.cs.entity.CsUserContactGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author swallowff
 * @create 2019/07/23
 */
@Repository
@Mapper
public interface CsUserContactGroupDao extends CrudDao<CsUserContactGroup> {

}