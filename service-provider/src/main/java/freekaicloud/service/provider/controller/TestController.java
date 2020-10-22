package freekaicloud.service.provider.controller;

import freekai.cloud.common.FreeResponse;
import freekaicloud.service.provider.hystrix.StoreIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author dell
 */
@RestController
public class TestController {

    @Autowired
    StoreIntegration storeIntegration;

    public static final String HELLO = "hello";

    @GetMapping("/hello/{msg}")
    public FreeResponse helloMsg(@PathVariable String msg){
        return FreeResponse.ok(HELLO + msg);
    }

    @GetMapping("/testHystrix")
    public String getStores(@RequestBody  Map<String,Object> parameteres){
        return storeIntegration.getStores(parameteres);
    }

    /**
     * 异步结果调用
     * @return
     */
    @GetMapping("/testAsync")
    public String testAsyncMethod(){
        try {
            return storeIntegration.executeAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
