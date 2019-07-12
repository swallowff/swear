package cn.swallowff.code.builder;

import cn.swallowff.code.GenFileType;
import cn.swallowff.code.entity.GeneratorFile;

/**
 * @author shenyu
 * @create 2019/7/12
 */
public class GeneratorFileBuilder {
    private GeneratorFile generatorFile = new GeneratorFile();

    private GeneratorFileBuilder name(String name){
        generatorFile.setName(name);
        return this;
    }

    private GeneratorFileBuilder nameSuffix(String nameSuffix){
        generatorFile.setNameSuffix(nameSuffix);
        return this;
    }

    private GeneratorFileBuilder fileSuffix(String fileSuffix){
        generatorFile.setFileSuffix(fileSuffix);
        return this;
    }

    private GeneratorFileBuilder moduleName(String moduleName){
        generatorFile.setModuleName(moduleName);
        return this;
    }

    private GeneratorFileBuilder templateName(String templateName){
        generatorFile.setTemplateName(templateName);
        return this;
    }

    private GeneratorFileBuilder genFileType(GenFileType genFileType){
        generatorFile.setFileType(genFileType);
        return this;
    }

    public GeneratorFile build(){
        return generatorFile;
    }


}
