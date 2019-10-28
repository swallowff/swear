package cn.swallowff.admin.components.chat;

import cn.swallowff.admin.modules.cs.entity.CsUser;
import cn.swallowff.admin.modules.cs.service.CsUserService;
import cn.swallowff.admin.modules.cs.wrapper.mappings.CsUserMapping;
import cn.swallowff.modules.chat.custom.User;
import cn.swallowff.modules.chat.custom.inf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenyu
 * @create 2019/7/21
 */
@Service
public class ChatUserService implements UserService {
    @Autowired
    private CsUserService csUserService;

    @Override
    public User getUser(String uid) {
        CsUser csUser = csUserService.selectById(uid);
        if (null == csUser){
            return null;
        }
        CsUserMapping csUserMapping = CsUserMapping.CS_USER_MAPPING;
        return csUserMapping.toSocketUser(csUser);
    }
}
