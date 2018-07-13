package com.shevelev.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class App {
    private static ConfigurableApplicationContext ctx;
    private Event event;
    private Client client;
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggers;

    public App() {
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    @Autowired
    public void setEvent(Event event) {
        this.event = event;
    }

    @Autowired
    public void setClient(Client client) {
        this.client = client;
    }

    @Autowired
    @Qualifier("cacheFileEventLogger")
    public void setEventLogger(EventLogger eventLogger) {
        this.eventLogger = eventLogger;
    }

    @Autowired
    public void setLoggers(Map<EventType, EventLogger> loggers) {
        this.loggers = loggers;
    }

    public static void main(String[] args) throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = (App) ctx.getBean("app");
        int count = 0;
        for (EventType currentEventType : app.loggers.keySet()){
                app.logEvent(currentEventType, "Logger =" + currentEventType + " count =" + count++);
            }
        }

    private void logEvent(Event event) throws IOException {
        eventLogger.logEvent(event);
    }

    private void logEvent(EventType type, String msg) throws IOException {
        EventLogger logger = loggers.get(type);
        event = (Event) ctx.getBean("event");
        event.setMsg(msg);

        if (logger == null){
            logEvent(event);
            ctx.registerShutdownHook();
        }else {
            logger.logEvent(event);
        }
    }
}
