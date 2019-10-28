package cn.swallowff.admin.modules.cs.dao;

import cn.swallowff.admin.cmomon.dao.CrudDao;
import cn.swallowff.admin.modules.cs.entity.CsGroupTeam;
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