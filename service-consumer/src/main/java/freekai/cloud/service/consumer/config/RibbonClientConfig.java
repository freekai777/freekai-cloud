package freekai.cloud.service.consumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import freekai.cloud.service.ribbon.config.RibbonConfigTest1;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @RibbonClient 配置中的name应为 eureka中APPLICATION的name
@RibbonClient(name = "service-provider", configuration = RibbonConfigTest1.class)
public class RibbonClientConfig {
}
