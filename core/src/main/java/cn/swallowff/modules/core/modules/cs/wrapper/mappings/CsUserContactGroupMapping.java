package cn.swallowff.modules.core.modules.cs.wrapper.mappings;

import cn.swallowff.modules.core.modules.cs.dto.ChatFriendGroupDto;
import cn.swallowff.modules.core.modules.cs.entity.CsUserContactGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CsUserContactGroupMapping {
    CsUserContactGroupMapping MAPPING = Mappers.getMapper(CsUserContactGroupMapping.class);

    @Mapping(source = "groupName",target = "groupname")
    ChatFriendGroupDto toChatFriendGroupDto(CsUserContactGroup csUserContactGroup);
}
