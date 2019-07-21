package cn.swallowff.modules.core.modules.cs.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.modules.cs.dto.ChatUserDto;
import cn.swallowff.modules.core.modules.cs.entity.CsFriendGroupUser;
import cn.swallowff.modules.core.modules.cs.entity.CsUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Repository
@Mapper
public interface CsFriendGroupUserDao extends CrudDao<CsFriendGroupUser> {

    List<CsUser> findGroupUserJoin(String groupId);
}