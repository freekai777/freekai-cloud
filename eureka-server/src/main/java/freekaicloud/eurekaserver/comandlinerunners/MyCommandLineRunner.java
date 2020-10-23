package freekaicloud.eurekaserver.comandlinerunners;

import freekaicloud.eurekaserver.events.MySpringEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * If you need to run some specific code once the SpringApplication has started
 * ===> 如果你想在spring应用启动后【执行 '一次'某些特殊的代码】
 * 可以 实现 CommandLineRunner 接口， 加上@Component注解
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    ApplicationContext context;
    /**
     * 读取配置文件中的值
     */
    @Value("${self.javaVersion}")
    private String selfJavaVersion;

    @Override
    public void run(String... args) throws Exception {


        Long startTime = System.currentTimeMillis();
        System.out.println(startTime + "自定义CommandLineRunner 执行 ========" + Arrays.asList(args) + "; java.version:=====" + selfJavaVersion);

        // 发布一个事件 [发现还是 同步 的执行]
        MySpringEvent mySpringEvent = new MySpringEvent("name...");
        context.publishEvent(mySpringEvent);
        System.out.println(System.currentTimeMillis()+"自定义CommandLineRunner 结束 ========耗时：" +(System.currentTimeMillis() - startTime));
    }
}
