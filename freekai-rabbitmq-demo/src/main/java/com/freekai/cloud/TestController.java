package com.freekai.cloud;

import com.freekai.cloud.producer.MqMsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    MqMsgProducer producer;

    @GetMapping("/v1/sendMsg")
    public void sendMsg(@RequestParam("msg") String msg, @RequestParam("routeKey") String routeKey){

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        producer.sendMsg(map,routeKey);

    }
}
