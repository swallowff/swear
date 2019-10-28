package cn.swallowff.admin.modules.cs.wrapper.mappings;

import cn.swallowff.admin.modules.cs.dto.ChatFriendGroupDto;
import cn.swallowff.admin.modules.cs.entity.CsUserContactGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CsUserContactGroupMapping {
    CsUserContactGroupMapping MAPPING = Mappers.getMapper(CsUserContactGroupMapping.class);

    @Mapping(source = "groupName",target = "groupname")
    ChatFriendGroupDto toChatFriendGroupDto(CsUserContactGroup csUserContactGroup);
}
