package com.shevelev.spring.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:client.properties")
public class Client {
    private String id;
    private String fullName;

    public Client() {
    }

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    @Value("${id}")
    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    @Value("${name}")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        Client client = (Client) obj;

        return (id == client.id || (id != null && id.equals(client.getId()))) &&
                (fullName == client.fullName || (fullName != null && fullName.equals(client.getFullName())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        return  result;
    }
}
