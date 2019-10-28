package cn.swallowff.admin.modules.cs.controller;

import cn.swallowff.admin.cmomon.resp.LayPageResp;
import cn.swallowff.admin.cmomon.resp.PageResp;
import cn.swallowff.admin.cmomon.annotion.Permission;
import cn.swallowff.admin.cmomon.controller.BaseController;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.modules.cs.entity.CsUser;
import cn.swallowff.admin.modules.cs.service.CsUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/cs/csUser")
public class CsUserController extends BaseController {

    @Autowired
    private CsUserService csUserService;

    @RequestMapping(value = "list.html")
    @Permission(value = "cs-csUser-list")
    public String listHtml(){
        return "/pages/admin/cs/csUser/csUser-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/admin/cs/csUser/csUser-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        CsUser csUser = csUserService.selectById(id);
        model.addAttribute("csUser",csUser);
        return "/pages/admin/cs/csUser/csUser-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(CsUser csUser){
        PageResp<CsUser> pageResp = csUserService.findPage(csUser);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(CsUser csUser){
        BaseResp baseResp = BaseResp.newSuccess();
        csUserService.save(csUser);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(CsUser csUser){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == csUser || StringUtils.isBlank(csUser.getId())){
            return baseResp.paramsError();
        }
        int r = csUserService.updateSelective(csUser);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = csUserService.delete(id);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "batchDel")
    @ResponseBody
    public BaseResp batchDel(String[] ids){
        BaseResp baseResp = BaseResp.newSuccess();
        int count = 0;
        for (String id : ids){
            int r = csUserService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
