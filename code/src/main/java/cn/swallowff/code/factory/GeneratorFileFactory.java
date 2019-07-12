package cn.swallowff.code.factory;

import cn.swallowff.code.GenFileType;
import cn.swallowff.code.entity.GeneratorFile;

/**
 * @author shenyu
 * @create 2019/7/12
 */
public class GeneratorFileFactory {

    public static GeneratorFile createEntity(){
        return new GeneratorFile("实体类","",".java","entity","entity.btl",GenFileType.JAVA);
    }

    public static GeneratorFile createService(){
        return new GeneratorFile("Service层","Service",".java","service","service.btl",GenFileType.JAVA);
    }

    public static GeneratorFile createDao(){
        return new GeneratorFile("持久层Dao","Dao",".java","dao","dao.btl",GenFileType.JAVA);
    }

    public static GeneratorFile createController(){
        return new GeneratorFile("控制层","Controller",".java","controller","controller.btl",GenFileType.JAVA);
    }

    public static GeneratorFile createXmlMapper(){
        return new GeneratorFile("mapper映射文件","Mapper",".xml","","mapper.btl",GenFileType.MAPPER);
    }

    public static GeneratorFile createListHtml(){
        return new GeneratorFile("列表页","-list",".html","","list-html.btl",GenFileType.PAGE);
    }

    public static GeneratorFile createAddHtml(){
        return new GeneratorFile("添加页","-add",".html","","add-html.btl",GenFileType.PAGE);
    }

    public static GeneratorFile createEditHtml(){
        return new GeneratorFile("修改页","-edit",".html","","edit-html.btl",GenFileType.PAGE);
    }

    public static GeneratorFile createListJs(){
        return new GeneratorFile("列表页JS","-list",".js","","list-js.btl",GenFileType.JAVASCRIPT);
    }

    public static GeneratorFile createAddJs(){
        return new GeneratorFile("添加页JS","-add",".js","","add-js.btl",GenFileType.JAVASCRIPT);
    }

    public static GeneratorFile createEditJs(){
        return new GeneratorFile("修改页JS","-edit",".js","","edit-js.btl",GenFileType.JAVASCRIPT);
    }
}
