package cn.swallowff.admin.modules.cs.service;

import cn.swallowff.admin.cmomon.service.CrudService;
import cn.swallowff.admin.modules.cs.dto.ChatGroupTeamDto;
import cn.swallowff.admin.modules.cs.dao.CsGroupTeamUserDao;
import cn.swallowff.admin.modules.cs.entity.CsGroupTeamUser;
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
