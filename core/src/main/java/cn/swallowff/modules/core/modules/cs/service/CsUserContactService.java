package cn.swallowff.modules.core.modules.cs.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.modules.cs.dao.CsUserContactDao;
import cn.swallowff.modules.core.modules.cs.dto.ChatUserDto;
import cn.swallowff.modules.core.modules.cs.entity.CsUserContact;
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
