package online.agatstudio.bootconfigdemo.event;

import lombok.Getter;
import online.agatstudio.bootconfigdemo.Event;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolder extends ApplicationEvent {

    private final Event event;

    public EventHolder(Object source, Event event) {
        super(source);
        this.event = event;
    }
}
