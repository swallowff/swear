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
        generatorConfig.setTableName("cms_category");
        generatorConfig.setJavaLocation("cn.swallowff.web.cms");
        generatorConfig.setMapperLocation("mapper.cms");
        generatorConfig.setHtmlLocation("WEB-INF.view.pages.modules.cms");
        generatorConfig.setJsLocation("static.js.modules.cms");
        generatorConfig.setModuleLocation("web");
        DataSource dataSource = SpringContextHolder.getBean(DataSource.class);
        generatorConfig.setConnection(dataSource.getConnection());
        generatorConfig.setTemplatePath("/templates/base/");
        generatorConfig.setTitle("文章");
        generatorConfig.setTablePrefix("cms");
        IGenerator generator = new GeneratorImpl(generatorConfig);
        generator.execute();
        return BaseResp.newSuccess();
    }

}
