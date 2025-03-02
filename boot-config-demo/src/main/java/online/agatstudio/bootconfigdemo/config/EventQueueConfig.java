package online.agatstudio.bootconfigdemo.config;

import online.agatstudio.bootconfigdemo.EventQueue;
import online.agatstudio.bootconfigdemo.EventQueueWorker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EventQueue.class)
public class EventQueueConfig {

    @Bean
    public EventQueueWorker eventQueueWorker(EventQueue eventQueue, ApplicationEventPublisher publisher) {
        return new EventQueueWorker(eventQueue, publisher);
    }
}
