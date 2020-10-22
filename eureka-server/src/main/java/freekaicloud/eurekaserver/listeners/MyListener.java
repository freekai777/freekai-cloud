package freekaicloud.eurekaserver.listeners;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 1.自定义一个listener 并实现 ApplicationListener 接口 ==> 自动注册
 * 2.创建 META-INF/spring.factories 文件
 *      >2.1 添加内容：以 org.springframework.context.ApplicationListener 为key， value为当前类即 MyListener的类全路径
 *      org.springframework.context.ApplicationListener = freekaicloud.eurekaserver.listeners.MyListener
 */
public class MyListener implements ApplicationListener {
    public MyListener() {
        System.out.println("MyListener is successful running...===========");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("applicationEvent has been listened ===========" + applicationEvent.getClass());
    }
}
