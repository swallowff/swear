package cn.swallowff.code.gen;

import cn.swallowff.code.FileType;
import cn.swallowff.code.factory.TemplateDataFactory;
import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.entity.TemplateData;
import cn.swallowff.code.factory.FilePathFactory;
import cn.swallowff.code.resolver.MetaDataResolver;
import cn.swallowff.code.util.BeetlUtil;
import cn.swallowff.code.util.StreamUtil;
import cn.swallowff.common.io.FileUtils;
import cn.swallowff.common.mapper.BeanMapConvert;
import org.beetl.core.Template;

import java.io.*;
import java.nio.charset.Charset;
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

    public void init(){
        templateData = new TemplateDataFactory(new MetaDataResolver(generatorConfig)).createTemplateData();
        pathMap = new FilePathFactory(generatorConfig).createPathMap();
    }

    @Override
    public void doGen(){
        init();
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

    @Override
    public void genEntity() {
        writeFile(FileType.ENTITY);

    }

    @Override
    public void genDao() {
        writeFile(FileType.DAO);
    }

    @Override
    public void genService() {
        writeFile(FileType.SERVICE);
    }

    @Override
    public void genController() {
        writeFile(FileType.CONTROLLER);
    }

    @Override
    public void genXmlMapper() {
        writeFile(FileType.MAPPER);
    }

    @Override
    public void genListPage() {
        writeFile(FileType.LIST_HTML);
    }

    @Override
    public void genAddPage() {
        writeFile(FileType.ADD_HTML);
    }

    @Override
    public void genEditPage() {
        writeFile(FileType.EDIT_HTML);
    }

    @Override
    public void genListJs() {
        writeFile(FileType.LIST_JS);
    }

    @Override
    public void genAddJs() {
        writeFile(FileType.ADD_JS);
    }

    @Override
    public void genEditJs() {
        writeFile(FileType.EDIT_JS);
    }

    public void writeFile(FileType fileType){
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            FileUtils.createFile(pathMap.get(fileType));
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

    public Template getTemplate(FileType fileType) throws IOException {
        return BeetlUtil.getTemplate(fileType.getTemplateName(),generatorConfig.getTemplatePath(),"utf-8");
    }


}
