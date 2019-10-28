package cn.swallowff.admin.modules.cs.controller;

import cn.swallowff.admin.cmomon.resp.LayPageResp;
import cn.swallowff.admin.cmomon.resp.PageResp;
import cn.swallowff.admin.cmomon.annotion.Permission;
import cn.swallowff.admin.cmomon.controller.BaseController;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.modules.cs.entity.CsGroupTeam;
import cn.swallowff.admin.modules.cs.service.CsGroupTeamService;
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
@RequestMapping(value = "${swear.path.admin}/cs/csGroupTeam")
public class CsGroupTeamController extends BaseController {

    @Autowired
    private CsGroupTeamService csGroupTeamService;

    @RequestMapping(value = "list.html")
    @Permission(value = "cs-csGroupTeam-list")
    public String listHtml(){
        return "/pages/admin/cs/csGroupTeam/csGroupTeam-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/admin/cs/csGroupTeam/csGroupTeam-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        CsGroupTeam csGroupTeam = csGroupTeamService.selectById(id);
        model.addAttribute("csGroupTeam",csGroupTeam);
        return "/pages/admin/cs/csGroupTeam/csGroupTeam-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(CsGroupTeam csGroupTeam){
        if (csGroupTeam.getPage() == -1){
            return BaseResp.newSuccess().setData(csGroupTeamService.findList(csGroupTeam));
        }
        PageResp<CsGroupTeam> pageResp = csGroupTeamService.findPage(csGroupTeam);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(CsGroupTeam csGroupTeam){
        BaseResp baseResp = BaseResp.newSuccess();
        csGroupTeamService.save(csGroupTeam);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(CsGroupTeam csGroupTeam){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == csGroupTeam || StringUtils.isBlank(csGroupTeam.getId())){
            return baseResp.paramsError();
        }
        int r = csGroupTeamService.updateSelective(csGroupTeam);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = csGroupTeamService.delete(id);
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
            int r = csGroupTeamService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
