package cn.swallowff.code.util;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;

/**
 * TODO 封装一些beetl页面常用的工具
 * @author shenyu
 * @create 2019/3/15
 */
public class BeetlUtil {
    private ClasspathResourceLoader classpathResourceLoader;
    private Configuration configuration;

    public BeetlUtil (String templateDir,String charset){
        this.classpathResourceLoader = new ClasspathResourceLoader(templateDir,charset);
        try {
            configuration = Configuration.defaultConfiguration();
            configuration.setStatementStart("@");
            configuration.setStatementEnd(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Template getTemplate(String templateName){
        GroupTemplate groupTemplate = new GroupTemplate(classpathResourceLoader,configuration);
        groupTemplate.registerFunctionPackage("StringUtils",new StringUtils());
        return groupTemplate.getTemplate(templateName);
    }

}
