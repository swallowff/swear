package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.system.entity.Dept;
import cn.swallowff.web.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DeptServiceTest {

    @Autowired
    private DeptService deptService;

    @Test
    public void testSave(){
        Dept dept = deptService.selectById("1146733087980478464");
        Dept newDept = new Dept();
        newDept.setFullName("这是测试数据");
        newDept.setParent(dept);
        deptService.save(newDept);
    }

    @Test
    public void testFindChildren(){
        Dept parent = deptService.selectById("1146733087980478464");
        Dept result = deptService.findTree(parent);
        System.out.println(result);
    }


}