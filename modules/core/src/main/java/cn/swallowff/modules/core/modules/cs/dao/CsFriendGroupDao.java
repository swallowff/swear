package cn.swallowff.modules.core.modules.cs.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.modules.cs.dto.ChatFriendGroupDto;
import cn.swallowff.modules.core.modules.cs.entity.CsFriendGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Repository
@Mapper
public interface CsFriendGroupDao extends CrudDao<CsFriendGroup> {

    List<ChatFriendGroupDto> findFriendGroups(String csuid);
}