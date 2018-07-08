package com.shevelev.spring.core;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

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
        FileUtils.writeStringToFile(file,msg,true);
    }

    public void init(){
        this.file = new File(fileName);
        if (file.canWrite()){
            System.out.println("Yes, go write");
        }else {
            System.out.println("No, no, no stop!");
        }
    }
}
