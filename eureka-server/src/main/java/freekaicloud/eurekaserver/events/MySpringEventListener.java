package freekaicloud.eurekaserver.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Component
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {
    final ExecutorService executorService = Executors.newFixedThreadPool(4);
    @Override
    public void onApplicationEvent(MySpringEvent mySpringEvent) {
        executorService.execute(
                ()->{
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(" mySpringEvent 执行========" + mySpringEvent);
                }
        );
    }
}
