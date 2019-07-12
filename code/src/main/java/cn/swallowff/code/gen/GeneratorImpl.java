package cn.swallowff.code.gen;

import cn.swallowff.code.FileType;
import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.entity.TemplateData;
import cn.swallowff.code.exception.GenerationException;
import cn.swallowff.code.exception.InvalidConfigException;
import cn.swallowff.code.factory.FilePathFactory;
import cn.swallowff.code.factory.TemplateDataFactory;
import cn.swallowff.code.resolver.MetaDataResolver;
import cn.swallowff.code.util.BeetlUtil;
import cn.swallowff.code.util.PathUtils;
import cn.swallowff.code.util.StreamUtil;
import cn.swallowff.common.io.FileUtils;
import cn.swallowff.common.mapper.BeanMapConvert;
import org.apache.commons.collections.CollectionUtils;
import org.beetl.core.Template;
import org.beetl.core.exception.BeetlException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public class GeneratorImpl implements IGenerator {

    private TemplateData templateData;
    private Map<FileType,String> pathMap;
    private GeneratorConfig generatorConfig;
    private BeetlUtil beetlUtil;

    public GeneratorImpl(GeneratorConfig generatorConfig) {
        this.generatorConfig = generatorConfig;
    }

    protected void init() throws GenerationException{
        checkConfig();
        beetlUtil = new BeetlUtil(generatorConfig.getTemplatePath(),"utf-8");
        templateData = new TemplateDataFactory(new MetaDataResolver(generatorConfig)).createTemplateData();
        pathMap = new FilePathFactory(generatorConfig).createPathMap();
    }

    //TODO 验证配置文件
    protected void checkConfig() throws InvalidConfigException {
        File file = new File(PathUtils.PROJECT_ROOT + File.separator + generatorConfig.getModuleLocation());
        if (!file.isDirectory()){
            throw new InvalidConfigException("module location must be a path where exist in your project path , make sure it is a effective directory ");
        }
        if (CollectionUtils.isEmpty(generatorConfig.getGenFiles())){
            throw new InvalidConfigException("gen files must not be empty");
        }

    }

    @Override
    public void execute() throws GenerationException {
        init();
        List<FileType> genFiles = generatorConfig.getGenFiles();
        for (FileType fileType : genFiles){
            writeFile(fileType);
        }

    }

    protected void writeFile(FileType fileType) throws GenerationException{
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            // 获取模板
            Template template = getTemplate(fileType);
            BeetlException beetlException = template.validate();
            if (null != beetlException){
                throw new GenerationException("beetl template file not exist : " + generatorConfig.getTemplatePath() + fileType.getTemplateName());
            }
            boolean created = FileUtils.createFile(pathMap.get(fileType));
            if (!created && !generatorConfig.isForceCover()){
                return;
            }
            fos = new FileOutputStream(pathMap.get(fileType));
            osw = new OutputStreamWriter(fos,Charset.forName("utf-8"));
            bw = new BufferedWriter(osw,1024);
            // 数据绑定模板
            template.binding(BeanMapConvert.beanToMapObject(templateData));
            // 文件输出
            template.renderTo(bw);
        } catch (FileNotFoundException e1){
            e1.printStackTrace();
        } finally {
            StreamUtil.close(fos,osw,bw);
        }
    }

    protected Template getTemplate(FileType fileType) {
        return beetlUtil.getTemplate(fileType.getTemplateName());
    }


}
