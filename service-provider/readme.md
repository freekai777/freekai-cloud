



2020-09-17 增加Hystrix的整合
    1> 引入依赖        <dependency>
                           <groupId>org.springframework.cloud</groupId>
                           <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
                       </dependency>
    2> 编写了一个StoreIntegration类, 方法上添加  @HystrixCommand(fallbackMethod = "失败时执行的方法名")
