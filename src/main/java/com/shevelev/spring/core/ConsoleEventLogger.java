package com.shevelev.spring.core;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("consoleEventLogger")
@Scope("prototype")
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}

