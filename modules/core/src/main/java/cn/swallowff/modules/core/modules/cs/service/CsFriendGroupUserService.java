package cn.swallowff.modules.core.modules.cs.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.modules.cs.dao.CsFriendGroupUserDao;
import cn.swallowff.modules.core.modules.cs.dto.ChatUserDto;
import cn.swallowff.modules.core.modules.cs.entity.CsFriendGroupUser;
import cn.swallowff.modules.core.modules.cs.entity.CsUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Service
public class CsFriendGroupUserService extends CrudService<CsFriendGroupUserDao, CsFriendGroupUser> {

    public List<CsUser> findGroupUserJoin(String groupId) {
        return crudDao.findGroupUserJoin(groupId);
    }
}
