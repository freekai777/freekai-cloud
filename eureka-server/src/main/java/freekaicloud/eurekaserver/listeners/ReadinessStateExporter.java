package freekaicloud.eurekaserver.listeners;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听 springboot application 的state
 *
 * 应用 事件 & 监听器
 */
@Component
public class ReadinessStateExporter {

    @EventListener
    public void onStateChange(AvailabilityChangeEvent<ReadinessState> event) {
        System.out.println("event==============" + event);
        System.out.println("event.state==============" + event.getState());
    }
}
