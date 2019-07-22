package cn.swallowff.modules.core.chat;

import cn.swallowff.modules.chat.custom.User;
import cn.swallowff.modules.chat.custom.inf.UserService;
import cn.swallowff.modules.core.modules.cs.entity.CsUser;
import cn.swallowff.modules.core.modules.cs.service.CsUserService;
import cn.swallowff.modules.core.modules.cs.wrapper.mappings.CsUserMapping;
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
