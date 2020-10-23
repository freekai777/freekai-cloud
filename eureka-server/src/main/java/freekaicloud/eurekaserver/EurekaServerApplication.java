package freekaicloud.eurekaserver;

import freekaicloud.eurekaserver.mybeans.SingletonA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(EurekaServerApplication.class, args);
        final SingletonA bean = ctx.getBean(SingletonA.class);
        bean.getBeanB();
        bean.getBeanB();
        bean.getBeanB();
    }

}
