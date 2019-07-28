package cn.swallowff.common.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/28
 */
public class CmdExecuter {

    private static Logger logger = LoggerFactory.getLogger(CmdExecuter.class);

    public static void exec(List<String> cmd, CmdOutputGetter getter) {

        if (logger.isInfoEnabled()) {
            logger.info("exec command: ");
            StringBuilder sb = new StringBuilder();
            for (String c : cmd) {
                sb.append(c).append(" ");
            }
            logger.info(sb.toString());
        }

        //cmd操作部分
        try {
            ProcessBuilder builder = new ProcessBuilder();//创建新线程
            builder.command(cmd);//执行FFmpeg命令
            builder.redirectErrorStream(true);
            Process proc = builder.start();
            BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = stdout.readLine()) != null) {
                if (getter != null)
                    getter.dealLine(line);
            }
            proc.waitFor();
            stdout.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
