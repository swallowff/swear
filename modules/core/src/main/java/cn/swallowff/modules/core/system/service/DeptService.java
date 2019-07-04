package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.DeptDao;
import cn.swallowff.modules.core.system.dto.DeptRoleDto;
import cn.swallowff.modules.core.system.entity.Dept;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService extends CrudService<DeptDao, Dept> {

    public DeptRoleDto findTree(){
        DeptRoleDto topNode = new DeptRoleDto();
        topNode.setId("0");
        topNode.setLabel("根节点");
        List<DeptRoleDto> children = crudDao.findChildren(topNode);
        if (CollectionUtils.isEmpty(children)){
            return topNode;
        }else {
            updateChildrenNode(children);
        }
        topNode.setChildren(children);
        return topNode;
    }

//    public TreeNode findAllTree(){
//        TreeNode topNode = new TreeNode();
//        topNode.setId("0");
//        List<TreeNode> children = crudDao.findChildren(topNode);
//        if (CollectionUtils.isEmpty(children)){
//            return topNode;
//        }else {
//            updateChildrenNode(children);
//        }
//        topNode.setChildren(children);
//        return topNode;
//    }

    private void updateChildrenNode(List<DeptRoleDto> treeNodeList){
        for (int i = 0 ; i < treeNodeList.size() ; i++){
            List<DeptRoleDto> children = crudDao.findChildren(treeNodeList.get(i));
            if (CollectionUtils.isEmpty(children)){
                break;
            }
            updateChildrenNode(children);
            treeNodeList.get(i).setChildren(children);
        }

    }
}
