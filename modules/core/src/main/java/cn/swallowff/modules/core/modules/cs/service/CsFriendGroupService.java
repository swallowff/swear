package cn.swallowff.modules.core.modules.cs.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.modules.cs.dao.CsFriendGroupDao;
import cn.swallowff.modules.core.modules.cs.dto.ChatFriendGroupDto;
import cn.swallowff.modules.core.modules.cs.entity.CsFriendGroup;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Service
public class CsFriendGroupService extends CrudService<CsFriendGroupDao, CsFriendGroup> {

    public List<ChatFriendGroupDto> findFriendGroups(String csuid) {
        return crudDao.findFriendGroups(csuid);
    }
}
