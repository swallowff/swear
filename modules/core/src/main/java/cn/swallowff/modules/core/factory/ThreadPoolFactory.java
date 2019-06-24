package cn.swallowff.modules.core.factory;

import java.util.concurrent.*;

/**
 * @author Administrator
 * @description 线程池单例工厂
 * @create 2019/6/19
 */
public class ThreadPoolFactory {
    private static ExecutorService CACHED_POOL_INSTANCE = null;
    private static ExecutorService CUSTOM_POOL_INSTANCE = null;

    private ThreadPoolFactory(){
    }

    public static ExecutorService cachedExecutorService(){
        if (null == CACHED_POOL_INSTANCE){
            synchronized (ThreadPoolFactory.class){
                CACHED_POOL_INSTANCE = cachedThreadPool();
                return CACHED_POOL_INSTANCE;
            }
        }else {
            return CACHED_POOL_INSTANCE;
        }
    }

    public static ExecutorService customExecutorService(){
        if (null == CUSTOM_POOL_INSTANCE){
            synchronized (ThreadPoolFactory.class){
                CUSTOM_POOL_INSTANCE = customThreadPool();
                return CUSTOM_POOL_INSTANCE;
            }
        }else {
            return CUSTOM_POOL_INSTANCE;
        }
    }

    private static ExecutorService cachedThreadPool(){
        return Executors.newCachedThreadPool();
    }

    private static ExecutorService customThreadPool(){
        return new ThreadPoolExecutor(5,10,30L,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
    }

}
