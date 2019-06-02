package cn.swallowff.modules.core.beetl;

import cn.swallowff.common.collect.MapUtils;
import cn.swallowff.common.io.PropertiesUtils;
import cn.swallowff.modules.core.beetl.tag.DictSelectorTag;
import cn.swallowff.modules.core.beetl.util.BeetlUtil;
import cn.swallowff.modules.core.beetl.util.ShiroExt;
import cn.swallowff.modules.core.util.KaptchaUtil;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * beetl页面工具注册
 * @author shenyu
 * @create 2019/3/15
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {
    @Autowired
    private Environment env;

    @Autowired
    private DictSelectorTag dictSelectorTag;

    @Override
    public void initOther() {
        Map<String, Object> sharedVars = this.groupTemplate.getSharedVars();
        if (sharedVars == null){
            sharedVars = MapUtils.newHashMap();
        }
        //通过读取配置文件获取项目根路径
        String contextPath =  PropertiesUtils.getInstance().getProperty("swear-path-ctx");
        String adminPath =  PropertiesUtils.getInstance().getProperty("swear-path-admin");
        //通过读取配置文件获取项目前端路径
//        String frontPath = PropertiesUtils.getInstance().getProperty("swear-path-front");
        String staticPath = PropertiesUtils.getInstance().getProperty("swear-path-static");
        //最终路径
        sharedVars.put("adminPath", contextPath + adminPath);
        sharedVars.put("staticPath",contextPath + staticPath);
        groupTemplate.setSharedVars(sharedVars);

        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.registerFunctionPackage("tool", new BeetlUtil());
        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
        groupTemplate.registerTagFactory("dictSelector", () -> dictSelectorTag);

        groupTemplate.registerFunction("env", new Function() {
            @Override
            public String call(Object[] paras, Context ctx) {
                String key = (String) paras[0];
                String value = env.getProperty(key);
                if (value != null) {
                    return getStr(value);
                }
                if (paras.length == 2) {
                    return (String) paras[1];
                }
                return null;
            }

            String getStr(String str) {
                try {
                    return new String(str.getBytes("iso8859-1"), StandardCharsets.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
