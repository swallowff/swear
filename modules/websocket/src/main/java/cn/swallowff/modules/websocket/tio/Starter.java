package cn.swallowff.modules.websocket.tio;

import org.tio.utils.jfinal.P;

/**
 * @author Administrator
 * @description
 * @create 2019/7/18
 */
public class Starter {

    public static void main(String[] args) throws Exception {
        //加载属性文件
        P.use("app.properties");

        //启动http server，这个步骤不是必须的，但是为了用页面演示websocket，所以先启动http
//        if (P.getInt("start.http", 1) == 1) {
//            HttpServerInit.init();
//        }

        //启动websocket服务器
        ShowcaseWebsocketStarter.start();
    }

}
