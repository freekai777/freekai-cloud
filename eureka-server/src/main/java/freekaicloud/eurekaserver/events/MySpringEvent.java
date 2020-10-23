package freekaicloud.eurekaserver.events;

import org.springframework.context.ApplicationEvent;

public class MySpringEvent extends ApplicationEvent {

    private String name;

    public String getName() {
        return name;
    }

    public MySpringEvent setName(String name) {
        this.name = name;
        return this;
    }

    public MySpringEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "MySpringEvent{" +
                "name='" + name + '\'' +
                '}';
    }
}
