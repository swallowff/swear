package cn.swallowff.modules.core.system.controller;

import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.gen.GeneratorImpl;
import cn.swallowff.code.gen.IGenerator;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.constant.states.ResponseState;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.service.UserService;
import cn.swallowff.modules.core.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * @author shenyu
 * @create 19-5-31
 */
@Controller
@RequestMapping(value = "/a/test")
public class TestController {
    @Autowired
    private UserService userService;


    @RequestMapping("testOptional")
    @ResponseBody
    public BaseResp testOp(String userId){
        final BaseResp baseResp = BaseResp.newError();
        User user = userService.selectById(userId);

        Optional<User> userOp = Optional.ofNullable(user);
        BaseResp resp = userOp.map(item -> item.getId()).map(id -> {
            baseResp.success();
            baseResp.setData(userService.selectById(id));
            return baseResp;
        }).orElse(baseResp.putState(ResponseState.SYSTEM_ERROR));
        return resp;
    }

    @RequestMapping("testGenCode")
    @ResponseBody
    public BaseResp testGenCode() throws Exception{
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setTableName("test_gen");
        generatorConfig.setJavaLocation("cn.swallowff.modules.core.system");
        generatorConfig.setMapperLocation("mapper.system");
        generatorConfig.setHtmlLocation("WEB-INF.pages.admin.system");
        generatorConfig.setJsLocation("static.module.admin.system");
        generatorConfig.setModuleLocation("web");
        DataSource dataSource = SpringContextHolder.getBean(DataSource.class);
        generatorConfig.setConnection(dataSource.getConnection());
        generatorConfig.setTemplatePath("/WEB-INF/view/pages/template/gencode/");
        IGenerator generator = new GeneratorImpl(generatorConfig);
        generator.doGen();
        return BaseResp.newSuccess();
    }

}
