package freekai.cloud.service.consumer.controller;

import freekai.cloud.common.FreeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO 这里需要注意
 */
@RestController
public class TestController {

    public static final String SERVICE_URL = "http://service-provider";//"http://localhost:8082";

    /**
     * 当  SERVICE_URL = "http://localhost:8082" 并且 redisTemplate配置处加了 @LoadBalanced 会爆出 No instances available for localhost 错误
     * 解决: SERVICE_URL 改为 SERVICE_URL = [application.yml中 spring.application.Name] eureka集群中的APPLICATION
     */
    @Autowired
    RestTemplate restTemplate; // redisTemplate配置出如果加了 @LoadBalanced 注解，SERVICE_URL

    /**
     *
     * @param msg
     * @return
     */
    @GetMapping("/test/{msg}")
    public FreeResponse test(@PathVariable String msg){
        final FreeResponse forObject = restTemplate.getForObject(SERVICE_URL+"/hello/{s}", FreeResponse.class, msg);
        return forObject;
    }

    //
    @GetMapping("/test1/{msg}")
    public FreeResponse test1(@PathVariable String msg){
        final FreeResponse forObject = restTemplate.getForObject(SERVICE_URL+"/hello/{s}", FreeResponse.class, msg);
        return forObject;
    }

    @GetMapping("/test2/{msg}")
    public FreeResponse test2(@PathVariable String msg){
        final FreeResponse forObject = restTemplate.getForObject(SERVICE_URL+"/hello/{s}", FreeResponse.class, msg);
        return forObject;
    }

}
