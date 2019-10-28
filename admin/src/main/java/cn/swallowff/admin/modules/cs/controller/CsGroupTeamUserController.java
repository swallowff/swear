package cn.swallowff.admin.modules.cs.controller;

import cn.swallowff.admin.cmomon.resp.LayPageResp;
import cn.swallowff.admin.cmomon.resp.PageResp;
import cn.swallowff.admin.cmomon.annotion.Permission;
import cn.swallowff.admin.cmomon.controller.BaseController;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.modules.cs.entity.CsGroupTeamUser;
import cn.swallowff.admin.modules.cs.service.CsGroupTeamUserService;
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
@RequestMapping(value = "${swear.path.admin}/cs/csGroupTeamUser")
public class CsGroupTeamUserController extends BaseController {

    @Autowired
    private CsGroupTeamUserService csGroupTeamUserService;

    @RequestMapping(value = "list.html")
    @Permission(value = "cs-csGroupTeamUser-list")
    public String listHtml(){
        return "/pages/admin/cs/csGroupTeamUser/csGroupTeamUser-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/admin/cs/csGroupTeamUser/csGroupTeamUser-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        CsGroupTeamUser csGroupTeamUser = csGroupTeamUserService.selectById(id);
        model.addAttribute("csGroupTeamUser",csGroupTeamUser);
        return "/pages/admin/cs/csGroupTeamUser/csGroupTeamUser-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(CsGroupTeamUser csGroupTeamUser){
        if (csGroupTeamUser.getPage() == -1){
            return BaseResp.newSuccess().setData(csGroupTeamUserService.findList(csGroupTeamUser));
        }
        PageResp<CsGroupTeamUser> pageResp = csGroupTeamUserService.findPage(csGroupTeamUser);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(CsGroupTeamUser csGroupTeamUser){
        BaseResp baseResp = BaseResp.newSuccess();
        csGroupTeamUserService.save(csGroupTeamUser);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(CsGroupTeamUser csGroupTeamUser){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == csGroupTeamUser || StringUtils.isBlank(csGroupTeamUser.getId())){
            return baseResp.paramsError();
        }
        int r = csGroupTeamUserService.updateSelective(csGroupTeamUser);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = csGroupTeamUserService.delete(id);
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
            int r = csGroupTeamUserService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
