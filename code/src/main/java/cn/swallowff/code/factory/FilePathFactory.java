package cn.swallowff.code.factory;

import cn.swallowff.code.FileType;
import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.util.PathUtils;
import cn.swallowff.common.lang.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public class FilePathFactory {
    private GeneratorConfig config;

    public FilePathFactory(GeneratorConfig config) {
        this.config = config;
    }

    public Map<FileType,String> createPathMap(){
        Map<FileType,String> pathMap = new HashMap<>();
//        String className = StringUtils.capCamelCase(config.getTableName());
        String className = StringUtils.capCamelCase(config.getTableName().replace(config.getTablePrefix().concat("_"),""));

        String entity = getPath(FileType.ENTITY,className);
        String dao = getPath(FileType.DAO,className);
        String service = getPath(FileType.SERVICE,className);
        String ctrl = getPath(FileType.CONTROLLER,className);
        String mapper = getPath(FileType.MAPPER,className);
        String listHtml = getPath(FileType.LIST_HTML,className);
        String addHtml = getPath(FileType.ADD_HTML,className);
        String editHtml = getPath(FileType.EDIT_HTML,className);
        String listJs = getPath(FileType.LIST_JS,className);
        String addJs = getPath(FileType.ADD_JS,className);
        String editJs = getPath(FileType.EDIT_JS,className);

        pathMap.put(FileType.ENTITY,entity);
        pathMap.put(FileType.DAO,dao);
        pathMap.put(FileType.SERVICE,service);
        pathMap.put(FileType.CONTROLLER,ctrl);
        pathMap.put(FileType.MAPPER,mapper);
        pathMap.put(FileType.LIST_HTML,listHtml);
        pathMap.put(FileType.ADD_HTML,addHtml);
        pathMap.put(FileType.EDIT_HTML,editHtml);
        pathMap.put(FileType.LIST_JS,listJs);
        pathMap.put(FileType.ADD_JS,addJs);
        pathMap.put(FileType.EDIT_JS,editJs);
        return pathMap;
    }


    private String getPath(FileType fileType,String className){
        String uncapClassName = StringUtils.uncap(className);
        StringBuilder sb = new StringBuilder();
        sb.append(PathUtils.PROJECT_ROOT).append(PathUtils.packageToPath(config.getModuleLocation()));
        switch (fileType){
            case ENTITY:
            case DAO:
            case SERVICE:
            case CONTROLLER:
                sb.append(PathUtils.SRC_JAVA)
                        .append(PathUtils.packageToPath(config.getJavaLocation()))
                        .append(File.separator).append(fileType.getPackageName()).append(File.separator)
                        .append(className).append(fileType.getNameSuffix())
                        .append(fileType.getSuffix());
                break;
            case MAPPER:
                sb.append(PathUtils.SRC_RESOURCES)
                        .append(PathUtils.packageToPath(config.getMapperLocation()))
                        .append(File.separator).append(fileType.getPackageName())
                        .append(File.separator)
                        .append(className).append(fileType.getNameSuffix())
                        .append(fileType.getSuffix());
                break;
            case LIST_HTML:
            case ADD_HTML:
            case EDIT_HTML:
                sb.append(PathUtils.SRC_WEBAPPS)
                        .append(PathUtils.packageToPath(config.getHtmlLocation()))
                        .append(File.separator).append(uncapClassName).append(File.separator)
                        .append(uncapClassName)
                        .append(fileType.getNameSuffix())
                        .append(fileType.getSuffix());
                break;
            case LIST_JS:
            case ADD_JS:
            case EDIT_JS:
                sb.append(PathUtils.SRC_WEBAPPS)
                        .append(PathUtils.packageToPath(config.getJsLocation()))
                        .append(File.separator).append(uncapClassName).append(File.separator)
                        .append(uncapClassName)
                        .append(fileType.getNameSuffix())
                        .append(fileType.getSuffix());
                break;
        }
        return sb.toString();
    }

    public static void main(String[] args){
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setTableName("test_gen");
        generatorConfig.setJavaLocation("cn.swallowff.modules.core.system");
        generatorConfig.setMapperLocation("mapper.system");
        generatorConfig.setHtmlLocation("WEB-INF.pages.admin.system");
        generatorConfig.setJsLocation("static.module.admin.system");
        FilePathFactory filePathFactory = new FilePathFactory(generatorConfig);
        filePathFactory.createPathMap();
    }

}
