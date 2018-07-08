package com.shevelev.spring.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    Client client;
    EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }




    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        for (int i = 0; i < 2; i++) {
            Event event = (Event) ctx.getBean("event");
            event.setMsg("Denis Shevelev id = " + i);
            app.logEvent(event);
        }
        ctx.registerShutdownHook();
    }

    private void logEvent(Event event) throws IOException {
        eventLogger.logEvent(event);
    }

    /*private void logEvent(String msg){
        String message = msg.replaceAll(client.getId(),client.getFullName());
        consoleEventLogger.logEvent(message);

    }*/
}
