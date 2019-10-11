package cn.swallowff.modules.core.modules.cs.wrapper.mappings;

import cn.swallowff.modules.chat.custom.User;
import cn.swallowff.modules.core.modules.cs.dto.ChatUserDto;
import cn.swallowff.modules.core.modules.cs.entity.CsUser;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-11T21:15:44+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_74 (Oracle Corporation)"
)
public class CsUserMappingImpl implements CsUserMapping {

    @Override
    public ChatUserDto toChatUserDto(CsUser csUser) {
        if ( csUser == null ) {
            return null;
        }

        ChatUserDto chatUserDto = new ChatUserDto();

        chatUserDto.setUsername( csUser.getNickName() );
        chatUserDto.setId( csUser.getId() );
        if ( csUser.getStatus() != null ) {
            chatUserDto.setStatus( String.valueOf( csUser.getStatus() ) );
        }
        chatUserDto.setAvatar( csUser.getAvatar() );
        chatUserDto.setSign( csUser.getSign() );

        return chatUserDto;
    }

    @Override
    public User toSocketUser(CsUser csUser) {
        if ( csUser == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( csUser.getNickName() );
        user.setId( csUser.getId() );
        user.setAvatar( csUser.getAvatar() );

        return user;
    }
}
