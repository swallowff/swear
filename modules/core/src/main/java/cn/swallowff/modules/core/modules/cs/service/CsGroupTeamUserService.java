package cn.swallowff.modules.core.modules.cs.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.modules.cs.dao.CsGroupTeamUserDao;
import cn.swallowff.modules.core.modules.cs.dto.ChatGroupTeamDto;
import cn.swallowff.modules.core.modules.cs.entity.CsGroupTeamUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Service
public class CsGroupTeamUserService extends CrudService<CsGroupTeamUserDao, CsGroupTeamUser> {

    public List<ChatGroupTeamDto> findUserGroupTeam(CsGroupTeamUser groupTeamUser){
        return crudDao.findUserGroupTeam(groupTeamUser);
    }

}
