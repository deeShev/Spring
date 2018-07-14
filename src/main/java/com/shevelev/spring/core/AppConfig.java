package com.shevelev.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.util.*;

@Configuration
@ComponentScan
@PropertySource("classpath:database.properties")
public class AppConfig {
    @Autowired
    Environment env;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), DateFormat.getDateInstance());
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap(ConsoleEventLogger consoleEventLogger,
                                                 CombinedEventLogger combinedEventLogger) {
        Map<EventType, EventLogger> eventTypeEventLoggerHashMap = new HashMap<>();
        eventTypeEventLoggerHashMap.put(EventType.INFO, consoleEventLogger);
        eventTypeEventLoggerHashMap.put(EventType.ERROR, combinedEventLogger);
        return eventTypeEventLoggerHashMap;
    }

    @Bean
    public Collection<EventLogger> loggers(ConsoleEventLogger consoleEventLogger,
                                           FileEventLogger fileEventLogger) {
        List<EventLogger> loggers = new ArrayList<>();
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }
}
