package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shenyu
 * @create 19-6-25
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "listHtml")
    public String listHtml(Model model){
        List<User> userList = userService.findList(new User());
        model.addAttribute("userList",userList);
        return "admin/pages/system/user-list";
    }

    @RequestMapping(value = "edit")
    @ResponseBody
    public BaseResp edit(User user){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == user || StringUtils.isBlank(user.getId())){
            return baseResp.paramsError();
        }
        int r = userService.updateSelective(user);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }


}
