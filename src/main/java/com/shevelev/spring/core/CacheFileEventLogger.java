package com.shevelev.spring.core;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger() {
        cache = new ArrayList<>();
    }

    @Value("2")
    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
    }

    @Override
    public void writeToFile(File file, String msg) throws IOException {
        for (Event currentEvent : cache) {
            FileUtils.writeStringToFile(file, currentEvent.getMsg(), true);
        }
    }

    private void writeEventsFromCache() throws IOException {
        if (cache.size() == cacheSize) {
            writeToFile(new File(super.getFileName()), null);
            cache.clear();
        }else {
            writeToFile(new File(super.getFileName()), null);
        }
    }

    @PreDestroy
    public void destroy() throws IOException {
        if (!cache.isEmpty()) {
            System.out.println("Start write and clear!");
            writeEventsFromCache();
        }else {
            System.out.println("This cache is null!");
        }
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }
}
