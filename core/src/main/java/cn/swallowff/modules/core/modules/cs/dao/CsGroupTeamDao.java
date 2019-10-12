package cn.swallowff.modules.core.modules.cs.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.modules.cs.entity.CsGroupTeam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Repository
@Mapper
public interface CsGroupTeamDao extends CrudDao<CsGroupTeam> {

}