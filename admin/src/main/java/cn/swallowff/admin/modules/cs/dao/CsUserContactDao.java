package cn.swallowff.admin.modules.cs.dao;

import cn.swallowff.admin.cmomon.dao.CrudDao;
import cn.swallowff.admin.modules.cs.dto.ChatUserDto;
import cn.swallowff.admin.modules.cs.entity.CsUserContact;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author swallowff
 * @create 2019/07/23
 */
@Repository
@Mapper
public interface CsUserContactDao extends CrudDao<CsUserContact> {

    List<ChatUserDto> findGroupContacts(String groupId);
}