package cn.swallowff.modules.core.logmgr;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shenyu
 * @create 2019/3/15
 */
public class LogManager {
    //日志记录操作延时
    private final int OPERATE_DELAY_MILLIS = 10;

    //异步操作记录日志的线程池
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    private LogManager(){}

    public static LogManager logManager = new LogManager();

    public static LogManager me(){return logManager;}

    public void executeLog(TimerTask task){
        executor.schedule(task,OPERATE_DELAY_MILLIS,TimeUnit.MICROSECONDS);
    }
}
