package com.shevelev.spring.core;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component("fileEventLogger")
@Scope("prototype")
@PropertySource("classpath:client.properties")
public class FileEventLogger implements EventLogger {
    @Value("${name}")
    private String fileName;
    private File file;

    public FileEventLogger() {
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void logEvent(Event event) throws IOException {
        file = new File(fileName);
        writeToFile(file,event.getMsg());
    }

    public void writeToFile(File file, String msg) throws IOException {
        FileUtils.writeStringToFile(file,msg + "\n",true);
    }

    @PostConstruct
    public void init(){
        this.file = new File(fileName);
        if (file.canWrite()){
            System.out.println("Yes, go write");
        }else {
            System.out.println("No, no, no stop!");
        }
    }
}
