package online.agatstudio.bootconfigdemo;

import lombok.RequiredArgsConstructor;
import online.agatstudio.bootconfigdemo.event.EventHolder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import java.util.UUID;

@RequiredArgsConstructor
public class EventQueueWorker {

    private final EventQueue eventQueue;
    private final ApplicationEventPublisher publisher; // отвечает за публикацию кастомного события в EventHolder

    @EventListener(ApplicationReadyEvent.class)
    // для того, чтобы метод вызвался сразу после загрузки контекста.
    // ApplicationReadyEvent событие генерируется, когда спринговый контекст
    // инициализирован и приложение полностью готово к работе.
    public void startEventQueue() {
        startEventProducer();
        startEventConsumer();
    }

    private void startEventProducer() {
        Thread eventProducerThread = new Thread(() -> {
            while (true) {
                try {
                    UUID id = UUID.randomUUID();
                    Event event = Event.builder()
                            .id(id)
                            .payload("Payload for event " + id)
                            .build();

                    eventQueue.enqueue(event);

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        eventProducerThread.start();
    }

    private void startEventConsumer() {
        Thread eventConsumerThread = new Thread(() -> {
            while (true) {
                Event event = eventQueue.dequeue();
                publisher.publishEvent(new EventHolder(this, event)); // публикует кастомное событие
            }
        });
        eventConsumerThread.start();
    }
}
