package online.agatstudio.bootconfigdemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@ConditionalOnProperty("app.event-queue.enabled")
public class EventQueue {

    private final BlockingQueue<Event> queue = new LinkedBlockingQueue<>();

    public void enqueue(Event event) {
        try {
            queue.put(event);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Event dequeue() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

}
