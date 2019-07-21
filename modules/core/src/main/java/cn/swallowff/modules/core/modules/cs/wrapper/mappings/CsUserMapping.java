package cn.swallowff.modules.core.modules.cs.wrapper.mappings;

import cn.swallowff.modules.core.modules.cs.dto.ChatUserDto;
import cn.swallowff.modules.core.modules.cs.entity.CsUser;
import cn.swallowff.modules.demo.io.tio.usecase.common.User;
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
