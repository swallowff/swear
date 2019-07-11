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
import org.beetl.core.Template;

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

    public GeneratorImpl(GeneratorConfig generatorConfig) {
        this.generatorConfig = generatorConfig;
    }

    protected void init() throws InvalidConfigException{
        checkConfig();
        templateData = new TemplateDataFactory(new MetaDataResolver(generatorConfig)).createTemplateData();
        pathMap = new FilePathFactory(generatorConfig).createPathMap();
    }

    //TODO 验证配置文件
    protected void checkConfig() throws InvalidConfigException {
        File file = new File(PathUtils.PROJECT_ROOT + File.separator + generatorConfig.getModuleLocation());
        if (!file.isDirectory()){
            throw new InvalidConfigException("module location must be a path where exist in your project path , make sure it is a effective directory ");
        }

    }

    @Override
    public void execute() throws GenerationException {
        init();
        List<FileType> genFiles = generatorConfig.getGenFiles();
        if (genFiles.contains(FileType.ENTITY)){

        }
        if (genFiles.contains(FileType.DAO)){

        }
        if (genFiles.contains(FileType.SERVICE)){

        }
        if (genFiles.contains(FileType.CONTROLLER)){

        }
        if (genFiles.contains(FileType.MAPPER)){

        }
        if (genFiles.contains(FileType.LIST_HTML)){

        }
        if (genFiles.contains(FileType.ADD_HTML)){

        }
        if (genFiles.contains(FileType.EDIT_HTML)){

        }
        if (genFiles.contains(FileType.LIST_JS)){

        }
        if (genFiles.contains(FileType.ADD_JS)){

        }
        if (genFiles.contains(FileType.EDIT_JS)){

        }

        genEntity();
        genDao();
        genService();
        genController();
        genXmlMapper();
        genListPage();
        genAddPage();
        genEditPage();
        genListJs();
        genAddJs();
        genEditJs();
    }

    protected void genEntity() {
        writeFile(FileType.ENTITY);
    }

    protected void genDao() {
        writeFile(FileType.DAO);
    }

    protected void genService() {
        writeFile(FileType.SERVICE);
    }

    protected void genController() {
        writeFile(FileType.CONTROLLER);
    }

    protected void genXmlMapper() {
        writeFile(FileType.MAPPER);
    }

    protected void genListPage() {
        writeFile(FileType.LIST_HTML);
    }

    protected void genAddPage() {
        writeFile(FileType.ADD_HTML);
    }

    protected void genEditPage() {
        writeFile(FileType.EDIT_HTML);
    }

    protected void genListJs() {
        writeFile(FileType.LIST_JS);
    }

    protected void genAddJs() {
        writeFile(FileType.ADD_JS);
    }

    protected void genEditJs() {
        writeFile(FileType.EDIT_JS);
    }

    protected void writeFile(FileType fileType){
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            boolean created = FileUtils.createFile(pathMap.get(fileType));
            if (!created && !generatorConfig.isForceCover()){
                return;
            }
//            FileUtils.createDirectory(StringUtils.substringBeforeLast(pathMap.get(fileType),"/"),);
            fos = new FileOutputStream(pathMap.get(fileType));
            osw = new OutputStreamWriter(fos,Charset.forName("utf-8"));
            bw = new BufferedWriter(osw,1024);
            // 获取模板
            Template template = getTemplate(fileType);
            // 数据绑定模板
            template.binding(BeanMapConvert.beanToMapObject(templateData));
            // 文件输出
            template.renderTo(bw);
        } catch (FileNotFoundException e1){

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            StreamUtil.close(fos,osw,bw);
        }
    }

    protected Template getTemplate(FileType fileType) throws IOException {
        return BeetlUtil.getTemplate(fileType.getTemplateName(),generatorConfig.getTemplatePath(),"utf-8");
    }


}
