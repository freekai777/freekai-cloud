package freekai.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/freekaiGateWay")
    public String gateWayMethod1(){
        return "--from gateway";
    }
}
