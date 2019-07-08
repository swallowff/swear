package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.TreeService;
import cn.swallowff.modules.core.system.dao.MenuDao;
import cn.swallowff.modules.core.system.dto.DtreeNode;
import cn.swallowff.modules.core.system.entity.Menu;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author swallowff
 * @description
 * @create 2019/6/29
 */
@Service
public class MenuService extends TreeService<MenuDao,Menu> {

    public Menu selectByCode(String code){
        Menu menu = new Menu();
        menu.setCode(code);
        return super.findEntity(menu);
    }

    @Override
    protected void updateChildrenNode(List<Menu> treeNodeList, Menu parent) {
        for (int i = 0; i < treeNodeList.size() ; i++){
            Menu menu = treeNodeList.get(i);
            menu.setOrderBy(parent.getOrderBy());
            menu.setUserId(parent.getUserId());
            List<Menu> children = crudDao.findChildren(menu);
            if (null != children && children.size() != 0){
                updateChildrenNode(children,parent);
                menu.setChildren(children);
            }
        }
    }

    public List<DtreeNode> findMenuListWithRole(Menu menu, String roleId) {
        return crudDao.findMenuListWithRole(menu,roleId);
    }
}
