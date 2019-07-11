package cn.swallowff.modules.core.system.controller;

import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.exception.GenerationException;
import cn.swallowff.code.exception.InvalidConfigException;
import cn.swallowff.code.gen.GeneratorImpl;
import cn.swallowff.code.gen.IGenerator;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.system.dto.GenCodeDto;
import cn.swallowff.modules.core.system.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author shenyu
 * @create 2019/7/11
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/sys/code")
public class CodeController extends BaseController {
    @Autowired
    private CodeService codeService;

    @RequestMapping(value = "list.html")
    public String listHtml(){
        return "admin/pages/system/code/code-list";
    }

    @RequestMapping(value = "tableList")
    @ResponseBody
    public Object tableList(){
        BaseResp baseResp = BaseResp.newSuccess();
        List<Map<String,Object>> respMap = codeService.getAllTables();
        return baseResp.putSuccess(respMap);
    }

    @PostMapping(value = "execute")
    @ResponseBody
    public Object execute(@Validated GenCodeDto genCodeDto, BindingResult bindingResult){
        BaseResp baseResp = BaseResp.newSuccess();
        if (!validateBindingResult(bindingResult,baseResp)){
            return baseResp;
        }
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setTableName(genCodeDto.getTable());
        generatorConfig.setForceCover(genCodeDto.isForceCover());
        generatorConfig.setTablePrefix(genCodeDto.getTablePrefix());

        generatorConfig.setJavaLocation(genCodeDto.getJavaFileLocation());
        generatorConfig.setMapperLocation(genCodeDto.getMapperFileLocation());
        generatorConfig.setHtmlLocation(genCodeDto.getHtmlFileLocation());
        generatorConfig.setJsLocation(genCodeDto.getJsFileLocation());
        generatorConfig.setModuleLocation(genCodeDto.getModule());
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
