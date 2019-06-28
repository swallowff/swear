package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.constant.states.ResponseState;
import cn.swallowff.modules.core.excepiton.BizException;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.service.UserService;
import cn.swallowff.modules.core.system.wrapper.UserAjaxListDictWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
        return "admin/pages/system/user-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(User user,Model model){
        return "admin/pages/system/user-add";
    }

    @RequestMapping(value = "userList.ajax")
    @ResponseBody
    public Object userListAjax(HttpServletRequest request){
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        User user = new User();
        user.setPageNum(page);
        user.setPageSize(limit);
//        List<User> list = userService.findList(user);
        PageResp<User> pageResp = userService.findPage(user);
        UserAjaxListDictWrapper wrapper = new UserAjaxListDictWrapper(pageResp.getDataList());
        List<Map<String,Object>> wrapList = wrapper.wrapList();
        LayPageResp layPageResp = LayPageResp.newSuccess();
        layPageResp.setData(wrapList);
        layPageResp.setCount(wrapList.size());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
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

    @RequestMapping(value = "edit.ajax")
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

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = userService.delete(id);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }


}
