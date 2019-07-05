package cn.swallowff.modules.core.system.wrapper;

import cn.swallowff.modules.core.cmomon.wrapper.BaseWrapper;
import cn.swallowff.modules.core.system.dto.DtreeNode;
import cn.swallowff.modules.core.system.entity.Dept;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/6
 */
public class DeptDtreeNodeWrapper extends BaseWrapper<Dept> {

    public DeptDtreeNodeWrapper(List<Dept> entityList) {
        super(entityList);
    }

    @Override
    protected <R> R wrapEntity(Dept dept) {
        DtreeNode dtreeNode = new DtreeNode();
        dtreeNode.setId(dept.getId());
        dtreeNode.setParentId(dept.getPid());
        dtreeNode.setTitle(dept.getFullName());
        return (R) dtreeNode;
    }
}
