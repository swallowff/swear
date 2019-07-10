package cn.swallowff.code.util;

import cn.swallowff.common.lang.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import java.io.IOException;

/**
 * TODO 封装一些beetl页面常用的工具
 * @author shenyu
 * @create 2019/3/15
 */
public class BeetlUtil {

    public static Template getTemplate(String templateName, String path, String charset) throws IOException {
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader(path,charset);
        Configuration configuration = Configuration.defaultConfiguration();
        configuration.setStatementStart("@");
        configuration.setStatementEnd(null);
        GroupTemplate groupTemplate = new GroupTemplate(classpathResourceLoader,configuration);
        groupTemplate.registerFunctionPackage("StringUtils",new StringUtils());
        return groupTemplate.getTemplate(templateName);
    }

    public static GroupTemplate getResourceGroupTemplate() throws IOException{
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader("/view","utf-8");
        Configuration configuration = Configuration.defaultConfiguration();
        return new GroupTemplate(classpathResourceLoader,configuration);
    }
}
