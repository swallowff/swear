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
                if (StringUtils.isNotBlank(config.getJavaRoot())){
                    sb.append(config.getJavaRoot());
                }else {
                    sb.append(PathUtils.SRC_JAVA);
                }
                sb.append(config.getJavaRelaPath())
                    .append(File.separator).append(fileType.getPackageName()).append(File.separator)
                    .append(className).append(fileType.getNameSuffix())
                    .append(fileType.getSuffix());
                break;
            case MAPPER:
                if (StringUtils.isNotBlank(config.getMapperRoot())){
                    sb.append(config.getMapperRoot());
                }else {
                    sb.append(PathUtils.SRC_RESOURCES);
                }
                sb.append(PathUtils.SRC_RESOURCES)
                        .append(config.getMapperRelaPath())
                        .append(File.separator).append(fileType.getPackageName())
                        .append(File.separator)
                        .append(className).append(fileType.getNameSuffix())
                        .append(fileType.getSuffix());
                break;
            case LIST_HTML:
            case ADD_HTML:
            case EDIT_HTML:
                sb.append(PathUtils.SRC_WEBAPPS)
                        .append(config.getHtmlRelaPath())
                        .append(File.separator).append(uncapClassName).append(File.separator)
                        .append(uncapClassName)
                        .append(fileType.getNameSuffix())
                        .append(fileType.getSuffix());
                break;
            case LIST_JS:
            case ADD_JS:
            case EDIT_JS:
                sb.append(PathUtils.SRC_WEBAPPS)
                        .append(config.getJsRelaPath())
                        .append(File.separator).append(uncapClassName).append(File.separator)
                        .append(uncapClassName)
                        .append(fileType.getNameSuffix())
                        .append(fileType.getSuffix());
                break;
        }
        return sb.toString();
    }

    public Map<GeneratorFile,String> createPathMap(Set<GeneratorFile> genFiles,String className){
        Map<GeneratorFile,String> pathMap = new HashMap<>();
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
            sb.append(config.getModuleLocation());
        }
        switch (generatorFile.getFileType()){
            case JAVA:
                if (StringUtils.isNotBlank(config.getJavaRoot())){
                    sb.append(config.getJavaRoot());
                }else {
                    sb.append(PathUtils.SRC_JAVA);
                }
                sb.append(config.getJavaRelaPath())
                    .append(File.separator).append(generatorFile.getModuleName()).append(File.separator)
                    .append(className).append(generatorFile.getNameSuffix())
                    .append(generatorFile.getFileSuffix());
                break;
            case MAPPER:
                if (StringUtils.isNotBlank(config.getMapperRoot())){
                    sb.append(config.getMapperRoot());
                }else {
                    sb.append(PathUtils.SRC_RESOURCES);
                }
                sb.append(config.getMapperRelaPath())
                    .append(File.separator)
                    .append(className).append(generatorFile.getNameSuffix())
                    .append(generatorFile.getFileSuffix());
                break;
            case PAGE:
                if (StringUtils.isNotBlank(config.getHtmlRoot())){
                    sb.append(config.getHtmlRoot());
                }else {
                    sb.append(PathUtils.SRC_WEBAPPS);
                }
                sb.append(config.getHtmlRelaPath())
                    .append(File.separator).append(uncapClassName).append(File.separator)
                    .append(uncapClassName)
                    .append(generatorFile.getNameSuffix())
                    .append(generatorFile.getFileSuffix());
                break;
            case JAVASCRIPT:
                if (StringUtils.isNotBlank(config.getJsRoot())){
                    sb.append(config.getJsRoot());
                }else {
                    sb.append(PathUtils.SRC_WEBAPPS);
                }
                sb.append(config.getJsRelaPath())
                    .append(File.separator).append(uncapClassName).append(File.separator)
                    .append(uncapClassName)
                    .append(generatorFile.getNameSuffix())
                    .append(generatorFile.getFileSuffix());
                break;
        }
        return sb.toString();
    }


}
