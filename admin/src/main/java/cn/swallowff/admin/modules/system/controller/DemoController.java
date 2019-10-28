package cn.swallowff.admin.modules.system.controller;

import cn.swallowff.admin.cmomon.entity.TreeEntity;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.cmomon.resp.LayPageResp;
import cn.swallowff.admin.cmomon.resp.PageResp;
import cn.swallowff.admin.config.properties.SwearEnvProperties;
import cn.swallowff.admin.modules.cs.entity.CsUser;
import cn.swallowff.admin.modules.cs.service.CsUserService;
import cn.swallowff.admin.modules.system.entity.Dept;
import cn.swallowff.admin.modules.system.service.DeptService;
import cn.swallowff.admin.modules.system.wrapper.DeptDtreeNodeWrapper;
import cn.swallowff.admin.components.shiro.ShiroKit;
import cn.swallowff.admin.components.shiro.ShiroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/5
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/demo")
public class DemoController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private CsUserService csUserService;
    @Autowired
    private SwearEnvProperties swearEnvProperties;

    @RequestMapping("demo.html")
    public String demoHtml() {
        return "/pages/admin/system/demo/demo";
    }

    @RequestMapping("layim.html")
    public String layimHtml(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        CsUser q = new CsUser();
        q.setSysUid(shiroUser.getId());
        CsUser csUser = csUserService.findEntity(q);
        if (null != csUser){
            model.addAttribute("csuid",csUser.getId());
            model.addAttribute("websocketUrl",swearEnvProperties.getWebsocketUrl());
        }
        return "/pages/admin/system/demo/layim";
    }

    @RequestMapping("video.html")
    public String videoHtml(Model model) {
        return "/pages/admin/system/demo/video";
    }

    @RequestMapping("mobile.html")
    public String mobileHtml(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        CsUser q = new CsUser();
        q.setSysUid(shiroUser.getId());
        CsUser csUser = csUserService.findEntity(q);
        if (null != csUser){
            model.addAttribute("csuid",csUser.getId());
            model.addAttribute("websocketUrl",swearEnvProperties.getWebsocketUrl());
        }
        return "/pages/admin/system/demo/mobile";
    }

    @RequestMapping(value = "treeTableList.ajax")
    @ResponseBody
    public Object treeTableList(Dept dept) {
        List<Dept> deptList = deptService.findList(new Dept());
        return deptList;
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(Dept dept) {
        PageResp<Dept> pageResp = deptService.findPage(dept);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(), pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "dtree.ajax")
    @ResponseBody
    public Object dtree(Dept dept) {
        BaseResp baseResp = BaseResp.newSuccess();
        dept.setPids(TreeEntity.ROOT_ID);
        List<Dept> deptList = deptService.findList(dept);
        DeptDtreeNodeWrapper dtreeNodeWrapper = new DeptDtreeNodeWrapper(deptList);
        return baseResp.setData(dtreeNodeWrapper.wrapList());
    }

    @RequestMapping(value = "eleTree.ajax")
    @ResponseBody
    public Object eleTree() {
        BaseResp baseResp = BaseResp.newSuccess();
        Dept dept = deptService.findAllTree();
        return baseResp.setData(dept);
    }

}
