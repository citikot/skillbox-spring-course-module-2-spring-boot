package online.agatstudio.bootconfigdemo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHolderListener {

    @EventListener
    public void listen(EventHolder eventHolder) {
        System.out.println("Listen method called");

        System.out.println(eventHolder.getEvent());
    }
}
