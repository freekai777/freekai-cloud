package freekaicloud.service.provider.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * hystrix demo类
 */
@Component
public class StoreIntegration {

    @HystrixCommand(fallbackMethod = "defaultGetStores", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")}
            )
    public String getStores(Map<String,Object> parameteres){
        if(parameteres.get("error")!=null){
            throw new RuntimeException("执行出错");
        }
        return "正常执行";
    }

    public String defaultGetStores(Map<String,Object> parameteres){

        return "hystrix fallback >>> default get stores";
    }

    /**
     * Hystrix 异步结果
     * @return
     */
    @HystrixCommand
    public Future<String> executeAsync(){
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "3秒后的结果";
            }
        };
    }
}
