package cn.swallowff.admin.modules.system.wrapper;

import cn.swallowff.admin.cmomon.wrapper.BaseWrapper;
import cn.swallowff.admin.modules.system.dto.DtreeNode;
import cn.swallowff.admin.modules.system.entity.Menu;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/6
 */
public class MenuDtreeNodeWrapper extends BaseWrapper<Menu> {

    public MenuDtreeNodeWrapper(List<Menu> entityList) {
        super(entityList);
    }

    @Override
    protected <R> R wrapEntity(Menu menu) {
        DtreeNode dtreeNode = new DtreeNode();
        dtreeNode.setId(menu.getId());
        dtreeNode.setParentId(menu.getPid());
        dtreeNode.setTitle(menu.getName());
        dtreeNode.setCheckArr("0");
        return (R) dtreeNode;
    }
}
