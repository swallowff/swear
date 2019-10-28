package cn.swallowff.admin.modules.cs.dao;

import cn.swallowff.admin.cmomon.dao.CrudDao;
import cn.swallowff.admin.modules.cs.dto.ChatGroupTeamDto;
import cn.swallowff.admin.modules.cs.entity.CsGroupTeamUser;
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