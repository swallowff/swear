package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.constant.states.ResponseState;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author shenyu
 * @create 19-5-31
 */
@Controller
@RequestMapping(value = "/a/test")
public class TestController {
    @Autowired
    private UserService userService;


    @RequestMapping("testOptional")
    @ResponseBody
    public BaseResp testOp(String userId){
        final BaseResp baseResp = BaseResp.newError();
        User user = userService.selectById(userId);

        Optional<User> userOp = Optional.ofNullable(user);
        BaseResp resp = userOp.map(item -> item.getId()).map(id -> {
            baseResp.success();
            baseResp.setData(userService.selectById(id));
            return baseResp;
        }).orElse(baseResp.putState(ResponseState.SYSTEM_ERROR));
        return resp;
    }

}
