package cn.swallowff.admin.modules.system.controller;

import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.entity.GeneratorFile;
import cn.swallowff.code.exception.GenerationException;
import cn.swallowff.code.factory.GeneratorFileFactory;
import cn.swallowff.code.gen.GeneratorImpl;
import cn.swallowff.code.gen.IGenerator;
import cn.swallowff.admin.cmomon.controller.BaseController;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.modules.system.dto.GenCodeDto;
import cn.swallowff.admin.modules.system.service.CodeService;
import cn.swallowff.admin.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shenyu
 * @create 2019/7/11
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/sys/code")
public class CodeController extends BaseController {
    @Autowired
    private CodeService codeService;

    private String mvcView = "WEB-INF.view";

    private String mvcStatic = "static";

    private String mapperLoation = "mapper";

    @RequestMapping(value = "list.html")
    public String listHtml() {
        return "/pages/admin/system/code/code-list";
    }

    @RequestMapping(value = "tableList")
    @ResponseBody
    public Object tableList() {
        BaseResp baseResp = BaseResp.newSuccess();
        List<Map<String, Object>> respMap = codeService.getAllTables();
        return baseResp.putSuccess(respMap);
    }

    @PostMapping(value = "execute")
    @ResponseBody
    public Object execute(@Validated GenCodeDto genCodeDto, BindingResult bindingResult) throws Exception {
        BaseResp baseResp = BaseResp.newSuccess();
        if (!validateBindingResult(bindingResult, baseResp)) {
            return baseResp;
        }
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setModuleLocation(genCodeDto.getRootModule());
        generatorConfig.setTableName(genCodeDto.getTable());
        generatorConfig.setTitle(genCodeDto.getTitle());
        generatorConfig.setCustomEntityName(genCodeDto.getEntityClassName());
        generatorConfig.setForceCover(genCodeDto.isForceCover());
        generatorConfig.setTablePrefix(genCodeDto.getTablePrefix());

        generatorConfig.setJavaRelaPath(genCodeDto.getRelaClassPath());
        generatorConfig.setMapperRelaPath(genCodeDto.getRelaMapperPath());
        generatorConfig.setHtmlRelaPath(genCodeDto.getRelaHtmlPath());
        generatorConfig.setJsRelaPath(genCodeDto.getRelaJsPath());
        generatorConfig.setConnection(SpringContextHolder.getBean(DataSource.class).getConnection());
        generatorConfig.setTemplatePath("/templates/base/");

        Set<GeneratorFile> fileSets = GeneratorFileFactory.createDefaultAll();
        generatorConfig.setGenFileSets(fileSets);
        IGenerator generator = new GeneratorImpl(generatorConfig);
        try {
            generator.execute();
        } catch (GenerationException e) {
            e.printStackTrace();
            return baseResp.putError(e.getMessage());
        }
        return baseResp;
    }

}
