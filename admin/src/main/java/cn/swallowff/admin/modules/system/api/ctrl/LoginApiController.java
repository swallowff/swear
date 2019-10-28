package cn.swallowff.admin.modules.system.api.ctrl;

import cn.swallowff.admin.components.shiro.service.UserAuthService;
import cn.swallowff.admin.modules.system.entity.User;
import cn.swallowff.admin.cmomon.controller.BaseController;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.cmomon.constant.exceptionenum.BizExceptionEnum;
import cn.swallowff.admin.cmomon.excepiton.BizException;
import cn.swallowff.admin.components.shiro.ShiroKit;
import cn.swallowff.admin.modules.system.api.req.LoginApiReq;
import cn.swallowff.admin.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @description
 * @create 2019/7/15
 */
@RestController
@RequestMapping("${swear.path.api}/auth")
public class LoginApiController extends BaseController {
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping(value = "")
    public Object auth(@Validated LoginApiReq loginApiReq, BindingResult bindingResult) {
        validateBindingResult(bindingResult);
        User user = userAuthService.user(loginApiReq.getAccount());
        if (null == user) {
            throw new BizException(BizExceptionEnum.NO_THIS_USER);
        }
        if (!StringUtils.equals(user.getPassword(), ShiroKit.md5(loginApiReq.getPassword(), user.getSalt()))) {
            throw new BizException(BizExceptionEnum.AUTH_REQUEST_ERROR);
        }

        String token = JwtTokenUtil.generateToken(user.getId());
        BaseResp baseResp = BaseResp.newSuccess();
        return baseResp.putSuccess(token);
    }

}
