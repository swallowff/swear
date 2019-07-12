package cn.swallowff.modules.core.system.controller;

import cn.swallowff.code.FileType;
import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.exception.GenerationException;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public BaseResp testGenCode() throws SQLException {
        BaseResp baseResp = BaseResp.newSuccess();
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setTableName("cms_content");
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
        generatorConfig.setForceCover(true);
        List<FileType> list = Arrays.asList(FileType.values());
        generatorConfig.setGenFiles(list);
        IGenerator generator = new GeneratorImpl(generatorConfig);
        try {
            generator.execute();
        } catch (GenerationException e) {
            e.printStackTrace();
            return baseResp.putError(e.getMessage());
        }
        return BaseResp.newSuccess();
    }

}
