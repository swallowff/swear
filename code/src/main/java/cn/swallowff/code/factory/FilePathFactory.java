package cn.swallowff.code.factory;

import cn.swallowff.code.FileType;
import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.entity.GeneratorFile;
import cn.swallowff.code.util.PathUtils;
import cn.swallowff.common.lang.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public class FilePathFactory {
    private GeneratorConfig config;

    public FilePathFactory(GeneratorConfig config) {
        this.config = config;
    }

    @Deprecated
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

    @Deprecated
    private String getPath(FileType fileType,String className){
        String uncapClassName = StringUtils.uncap(className);
        StringBuilder sb = new StringBuilder();
        sb.append(PathUtils.PROJECT_ROOT);
        if (StringUtils.isNotBlank(config.getModuleLocation())){
            sb.append(PathUtils.packageToPath(config.getModuleLocation()));
        }
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

    public Map<GeneratorFile,String> createPathMap(Set<GeneratorFile> genFiles){
        Map<GeneratorFile,String> pathMap = new HashMap<>();
        String className = "";
        String tablePrefix = config.getTablePrefix();
        if (null == tablePrefix){
            className = StringUtils.capCamelCase(config.getTableName());
        } else if (tablePrefix.endsWith("_")){
            className = StringUtils.capCamelCase(config.getTableName().replace(config.getTablePrefix(),""));
        }else {
            className = StringUtils.capCamelCase(config.getTableName().replace(config.getTablePrefix().concat("_"),""));
        }

        for (GeneratorFile generatorFile : genFiles){
            pathMap.put(generatorFile,getPath(generatorFile,className));
        }
        return pathMap;
    }

    private String getPath(GeneratorFile generatorFile, String className){
        String uncapClassName = StringUtils.uncap(className);
        StringBuilder sb = new StringBuilder();
        sb.append(PathUtils.PROJECT_ROOT);
        if (StringUtils.isNotBlank(config.getModuleLocation())){
            sb.append(PathUtils.packageToPath(config.getModuleLocation()));
        }
        switch (generatorFile.getFileType()){
            case JAVA:
                sb.append(PathUtils.SRC_JAVA)
                        .append(PathUtils.packageToPath(config.getJavaLocation()))
                        .append(File.separator).append(generatorFile.getModuleName()).append(File.separator)
                        .append(className).append(generatorFile.getNameSuffix())
                        .append(generatorFile.getFileSuffix());
                break;
            case MAPPER:
                sb.append(PathUtils.SRC_RESOURCES)
                        .append(PathUtils.packageToPath(config.getMapperLocation() + "." + config.getMapperModules()))
                        .append(File.separator)
                        .append(className).append(generatorFile.getNameSuffix())
                        .append(generatorFile.getFileSuffix());
                break;
            case PAGE:
                sb.append(PathUtils.SRC_WEBAPPS)
                        .append(PathUtils.packageToPath(config.getHtmlLocation() + "." + config.getHtmlModules()))
                        .append(File.separator).append(uncapClassName).append(File.separator)
                        .append(uncapClassName)
                        .append(generatorFile.getNameSuffix())
                        .append(generatorFile.getFileSuffix());
                break;
            case JAVASCRIPT:
                sb.append(PathUtils.SRC_WEBAPPS)
                        .append(PathUtils.packageToPath(config.getJsLocation() + "." + config.getJsModules()))
                        .append(File.separator).append(uncapClassName).append(File.separator)
                        .append(uncapClassName)
                        .append(generatorFile.getNameSuffix())
                        .append(generatorFile.getFileSuffix());
                break;
        }
        return sb.toString();
    }


}
