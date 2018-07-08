package com.shevelev.spring.core;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CachFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CachFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        cache.add(event);
    }

    @Override
    public void writeToFile(File file, String msg) throws IOException {
        for (Event currentEvent : cache) {
            FileUtils.writeStringToFile(file, currentEvent.getMsg(), true);
        }
    }

    public void writeEventsFromCache() throws IOException {
        if (cache.size() == cacheSize) {
            writeToFile(new File(super.getFileName()), null);
            cache.clear();
        }
    }

    public void destroy() throws IOException {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}
