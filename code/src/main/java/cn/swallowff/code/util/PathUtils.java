package cn.swallowff.code.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public class PathUtils {
    public static final String PROJECT_ROOT = new File("").getAbsolutePath();

    public static final String SRC_JAVA = File.separator + "src" + File.separator + "main" + File.separator + "java";
    public static final String SRC_RESOURCES = File.separator + "src" + File.separator + "main" + File.separator + "resources";
    public static final String SRC_WEBAPPS = File.separator + "src" + File.separator + "main" + File.separator + "webapps";

    public static String packageToPath(String var1){
        if (StringUtils.isBlank(var1)){
            return "";
        }
        String[] a = var1.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String str : a){
            sb.append(File.separator);
            sb.append(str);
        }
        return sb.toString();

    }


}
