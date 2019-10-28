package cn.swallowff.admin.modules.cs.wrapper.mappings;

import cn.swallowff.admin.modules.cs.dto.ChatUserDto;
import cn.swallowff.modules.chat.custom.User;
import cn.swallowff.admin.modules.cs.entity.CsUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CsUserMapping {
    CsUserMapping CS_USER_MAPPING = Mappers.getMapper(CsUserMapping.class);

    @Mapping(source = "nickName",target = "username")
    ChatUserDto toChatUserDto(CsUser csUser);

    @Mapping(source = "nickName",target = "username")
    User toSocketUser(CsUser csUser);

}
