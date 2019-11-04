package cn.swallowff.admin.modules.cs.wrapper.mappings;

import cn.swallowff.admin.modules.cs.dto.ChatFriendGroupDto;
import cn.swallowff.admin.modules.cs.entity.CsUserContactGroup;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-04T20:23:46+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_74 (Oracle Corporation)"
)
public class CsUserContactGroupMappingImpl implements CsUserContactGroupMapping {

    @Override
    public ChatFriendGroupDto toChatFriendGroupDto(CsUserContactGroup csUserContactGroup) {
        if ( csUserContactGroup == null ) {
            return null;
        }

        ChatFriendGroupDto chatFriendGroupDto = new ChatFriendGroupDto();

        chatFriendGroupDto.setGroupname( csUserContactGroup.getGroupName() );
        chatFriendGroupDto.setId( csUserContactGroup.getId() );

        return chatFriendGroupDto;
    }
}
