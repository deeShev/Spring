package com.shevelev.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.*;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Event event(){
        return new Event(new Date(),DateFormat.getDateInstance());
    }

    @Bean
    @Autowired
    public Map<EventType,EventLogger> loggerMap(ConsoleEventLogger consoleEventLogger,
                                                CombinedEventLogger combinedEventLogger){
        Map<EventType, EventLogger> eventTypeEventLoggerHashMap = new HashMap<>();
        eventTypeEventLoggerHashMap.put(EventType.INFO, consoleEventLogger);
        eventTypeEventLoggerHashMap.put(EventType.ERROR, combinedEventLogger);
        return eventTypeEventLoggerHashMap;
    }

    @Bean
    @Autowired
    public Collection<EventLogger> loggers(ConsoleEventLogger consoleEventLogger,
                                           FileEventLogger fileEventLogger){
        List<EventLogger> loggers = new ArrayList<>();
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }
}
