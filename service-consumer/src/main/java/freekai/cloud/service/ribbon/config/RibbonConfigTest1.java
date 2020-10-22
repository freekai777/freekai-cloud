package freekai.cloud.service.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfigTest1 {

    /**
     * 自定义一个配置类，返回一个Ribbon的随机访问规则
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        System.out.println("创建ribbon rule-----");
        return new RandomRule();
    }
}
