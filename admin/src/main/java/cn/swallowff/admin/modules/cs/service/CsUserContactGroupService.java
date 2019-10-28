package cn.swallowff.admin.modules.cs.service;

import cn.swallowff.admin.cmomon.service.CrudService;
import cn.swallowff.admin.modules.cs.dao.CsUserContactGroupDao;
import cn.swallowff.admin.modules.cs.entity.CsUserContactGroup;
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
