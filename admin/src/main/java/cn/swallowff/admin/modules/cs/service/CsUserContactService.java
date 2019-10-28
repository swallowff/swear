package cn.swallowff.admin.modules.cs.service;

import cn.swallowff.admin.cmomon.service.CrudService;
import cn.swallowff.admin.modules.cs.dto.ChatUserDto;
import cn.swallowff.admin.modules.cs.dao.CsUserContactDao;
import cn.swallowff.admin.modules.cs.entity.CsUserContact;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author swallowff
 * @create 2019/07/23
 */
@Service
public class CsUserContactService extends CrudService<CsUserContactDao, CsUserContact> {
    public List<ChatUserDto> findGroupContacts(String groupId){
        return crudDao.findGroupContacts(groupId);
    }

}
