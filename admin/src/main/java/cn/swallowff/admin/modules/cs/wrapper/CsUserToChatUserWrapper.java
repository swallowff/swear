package cn.swallowff.admin.modules.cs.wrapper;

import cn.swallowff.admin.cmomon.wrapper.BaseWrapper;
import cn.swallowff.admin.modules.cs.dto.ChatUserDto;
import cn.swallowff.admin.modules.cs.wrapper.mappings.CsUserMapping;
import cn.swallowff.admin.modules.cs.entity.CsUser;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/21
 */
public class CsUserToChatUserWrapper extends BaseWrapper<CsUser> {

    public CsUserToChatUserWrapper(CsUser csUser) {
        super(csUser);
    }

    public CsUserToChatUserWrapper(List<CsUser> entityList) {
        super(entityList);
    }

    @Override
    protected <R> R wrapEntity(CsUser csUser) {
        ChatUserDto chatUserDto = CsUserMapping.CS_USER_MAPPING.toChatUserDto(csUser);
        if (csUser.getIsOnline()){
            chatUserDto.setStatus("online");
        }else {
            chatUserDto.setStatus("hide");
        }
        return (R) chatUserDto;
    }
}
