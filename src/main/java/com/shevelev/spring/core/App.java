package com.shevelev.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    Client client;
    ConsoleEventLogger consoleEventLogger;

    public App(Client client, ConsoleEventLogger consoleEventLogger) {
        this.client = client;
        this.consoleEventLogger = consoleEventLogger;
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        event.setMsg("Denis");
        app.logEvent(event);

        //app.logEvent("Some event for user 1");


    }

    private void logEvent(Event event){
        consoleEventLogger.logEvent(event);
    }
    /*private void logEvent(String msg){
        String message = msg.replaceAll(client.getId(),client.getFullName());
        consoleEventLogger.logEvent(message);

    }*/
}
