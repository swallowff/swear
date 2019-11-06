/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package cn.swallowff.code.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author swallowff
 * @version 2019-3-16
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static boolean createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			logger.debug("文件 " + destFileName + " 已存在!");
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			logger.debug(destFileName + " 为目录，不能创建目录!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			// 如果文件所在的目录不存在，则创建目录
			if (!file.getParentFile().mkdirs()) {
				logger.debug("创建文件所在的目录失败!");
				return false;
			}
		}

		// 创建文件
		try {
			if (file.createNewFile()) {
				logger.debug(destFileName + " 文件创建成功!");
				return true;
			} else {
				logger.debug(destFileName + " 文件创建失败!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(destFileName + " 文件创建失败!");
			return false;
		}

	}

    
}
