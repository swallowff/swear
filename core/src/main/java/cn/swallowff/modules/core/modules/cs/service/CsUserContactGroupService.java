package cn.swallowff.modules.core.modules.cs.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.modules.cs.dao.CsUserContactGroupDao;
import cn.swallowff.modules.core.modules.cs.dto.ChatFriendGroupDto;
import cn.swallowff.modules.core.modules.cs.entity.CsUserContactGroup;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author swallowff
 * @create 2019/07/23
 */
@Service
public class CsUserContactGroupService extends CrudService<CsUserContactGroupDao, CsUserContactGroup> {

    public List<CsUserContactGroup> findUserGroups(String uid) {
        CsUserContactGroup q = new CsUserContactGroup();
        q.setUid(uid);
        return crudDao.findList(q);
    }
}
