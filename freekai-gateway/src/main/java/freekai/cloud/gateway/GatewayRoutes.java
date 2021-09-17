package freekai.cloud.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayRoutes {

    // 则访问当前项目时 前缀中加上"freekaiGateWay" 后面得路径为要跳转的项目对应的api 即完成路由访问 http://localhost:8091/freekaiGateWay/hello/123
    // 按照下方定义的规则，会把"freekaiGateWay"替换为空并路由到 http://localhost:8088/hello/123
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().route(r->
            r.path("/freekaiGateWay/**")
                    .filters(f->f.stripPrefix(1)) // 截取路由第一位
                    .uri("http://localhost:8088/")

         ).build();
    }
}
