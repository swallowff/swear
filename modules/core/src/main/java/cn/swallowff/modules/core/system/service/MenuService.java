package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.MenuDao;
import cn.swallowff.modules.core.system.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author swallowff
 * @description
 * @create 2019/6/29
 */
@Service
public class MenuService extends CrudService<MenuDao,Menu> {

    public List<Menu> findMenuTree(){
        Menu qEntity1 = new Menu();
        qEntity1.setLevels(1);
        qEntity1.setOrderBy("sort ASC");
        //查询所有一级菜单
        List<Menu> firstList = super.findList(qEntity1);
        for (Menu m : firstList){
            Menu qEntity2 = new Menu();
            qEntity2.setPcode(m.getCode());
            qEntity2.setOrderBy("sort ASC");
            List<Menu> secondList = super.findList(qEntity2);
            m.setSubMenuList(secondList);
        }
        return firstList;
    }

}
