package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.constant.states.ResponseState;
import cn.swallowff.modules.core.excepiton.BizException;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "list.html")
    public String listHtml(User user,Model model){
        return "admin/pages2/system/user-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(User user,Model model){
        PageResp<User> page = userService.findPage(user);
        model.addAttribute("page",page);
        return "admin/pages2/system/user-add";
    }

    @RequestMapping(value = "userList.ajax")
    @ResponseBody
    public Object userListAjax(HttpServletRequest request){
        LayPageResp layPageResp = LayPageResp.newSuccess();
        List<User> list = userService.findList(new User());
        layPageResp.setData(list);
        layPageResp.setCount(list.size());
        return layPageResp;
    }

    @RequestMapping(value = "addHtml")
    public String addHtml(){
        return "admin/pages/system/user-add";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public BaseResp add(User user){
        BaseResp baseResp = BaseResp.newSuccess();
        User existUser = userService.selectByAccount(user.getAccount());
        if (null != existUser){
            throw new BizException(ResponseState.USER_ACCOUNT_EXISTS);
        }
        userService.save(user);
        return baseResp;
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
