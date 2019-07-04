package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.system.entity.Dept2;
import cn.swallowff.web.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DeptService2Test {

    @Autowired
    private DeptService2 deptService2;

    @Test
    public void testSave(){
        Dept2 dept2 = deptService2.selectById("1146733087980478464");
        Dept2 newDept = new Dept2();
        newDept.setFullName("这是测试数据");
        newDept.setParent(dept2);
        deptService2.save(newDept);
    }

    @Test
    public void testFindChildren(){
        Dept2 parent = deptService2.selectById("1146733087980478464");
        Dept2 result = deptService2.findChildren(parent);
        System.out.println(result);
    }


}