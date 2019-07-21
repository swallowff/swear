package cn.swallowff.modules.core.modules.system.controller;

import cn.swallowff.modules.core.cmomon.entity.TreeEntity;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.modules.cs.entity.CsUser;
import cn.swallowff.modules.core.modules.cs.service.CsUserService;
import cn.swallowff.modules.core.modules.system.entity.Dept;
import cn.swallowff.modules.core.modules.system.service.DeptService;
import cn.swallowff.modules.core.modules.system.wrapper.DeptDtreeNodeWrapper;
import cn.swallowff.modules.core.shiro.ShiroKit;
import cn.swallowff.modules.core.shiro.ShiroUser;
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
        }
        return "/pages/admin/system/demo/layim";
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
