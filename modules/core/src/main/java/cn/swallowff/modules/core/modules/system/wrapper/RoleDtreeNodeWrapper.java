package cn.swallowff.modules.core.modules.system.wrapper;

import cn.swallowff.modules.core.cmomon.wrapper.BaseWrapper;
import cn.swallowff.modules.core.modules.system.dto.DtreeNode;
import cn.swallowff.modules.core.modules.system.entity.Role;
import cn.swallowff.modules.core.modules.system.wrapper.mappings.RoleMapping;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/6
 */
public class RoleDtreeNodeWrapper extends BaseWrapper<Role> {
    public RoleDtreeNodeWrapper(List<Role> entityList) {
        super(entityList);
    }

    @Override
    protected <R> R wrapEntity(Role role) {
//        DtreeNode dtreeNode = new DtreeNode();
//        dtreeNode.setId(role.getId());
//        dtreeNode.setParentId(role.getPid());
//        dtreeNode.setTitle(role.getName());
//        dtreeNode.setCheckArr("0");
//        return (R) dtreeNode;
        return (R) RoleMapping.ROLE_MAPPING.toDtreeNode(role);
    }
}
