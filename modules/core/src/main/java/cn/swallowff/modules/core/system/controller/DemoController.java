package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.entity.TreeEntity;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.system.entity.Dept;
import cn.swallowff.modules.core.system.service.DeptService;
import cn.swallowff.modules.core.system.wrapper.DeptDtreeNodeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("list.html")
    public String demoListHtml() {
        return "admin/pages/system/demo/demo";
    }

    @RequestMapping("layim.html")
    public String layimHtml() {
        return "admin/pages/system/demo/layim";
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
