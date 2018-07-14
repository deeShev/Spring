package com.shevelev.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DBLogger implements EventLogger {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void logEvent(Event event) throws IOException {
        jdbcTemplate.update("INSERT INTO client (ClientID, Fullname) VALUES (3,\"Vasia\")");
        int indexClient = jdbcTemplate.queryForObject("SELECT ClientID FROM client WHERE FullName = 'Vasia'",Integer.class);
        System.out.println(indexClient);
    }
}
