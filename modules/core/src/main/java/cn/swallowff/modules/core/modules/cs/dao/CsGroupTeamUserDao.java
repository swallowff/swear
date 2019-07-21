package cn.swallowff.modules.core.modules.cs.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.modules.cs.dto.ChatGroupTeamDto;
import cn.swallowff.modules.core.modules.cs.entity.CsGroupTeamUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Repository
@Mapper
public interface CsGroupTeamUserDao extends CrudDao<CsGroupTeamUser> {

    List<ChatGroupTeamDto> findUserGroupTeam(CsGroupTeamUser groupTeamUser);
}