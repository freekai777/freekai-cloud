package freekai.cloud.service.provider2.controller;

import freekai.cloud.common.FreeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dell
 */
@RestController
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    public static final String HELLO = "hello";

    @GetMapping("/hello/{msg}")
    public FreeResponse helloMsg(@PathVariable String msg){
        return FreeResponse.ok(HELLO + msg +"端口号："+serverPort);
    }

}
