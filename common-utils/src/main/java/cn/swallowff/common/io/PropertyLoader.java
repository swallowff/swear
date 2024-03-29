/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package cn.swallowff.common.io;

import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 配置文件加载（Boot）
 * @author swallowff
 * @version 2019-10-16
 */
public class PropertyLoader implements org.springframework.boot.env.PropertySourceLoader{
	
	private static boolean isLoadPropertySource = false;
	
	@Override
	public String[] getFileExtensions() {
		return new String[] { "properties", "yml" };
	}
	
	@Override
	public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
		if (!isLoadPropertySource){
			isLoadPropertySource = true;
			Properties properties = PropertiesUtils.getInstance().getProperties();
			return Collections.singletonList(new OriginTrackedMapPropertySource("swear", properties));
		}
		return Collections.emptyList();
	}
	
}
