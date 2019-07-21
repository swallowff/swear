package cn.swallowff.modules.core.chat;

import cn.swallowff.modules.core.modules.cs.entity.CsUser;
import cn.swallowff.modules.core.modules.cs.service.CsUserService;
import cn.swallowff.modules.core.modules.cs.wrapper.mappings.CsUserMapping;
import cn.swallowff.modules.demo.io.tio.usecase.common.User;
import cn.swallowff.modules.demo.io.tio.usecase.server.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenyu
 * @create 2019/7/21
 */
@Service
public class ChatUserService implements IUserService {
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
