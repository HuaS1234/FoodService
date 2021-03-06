package com.udacity.jdnd.course1exercises.lesson2.exercise1;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private List<String> messages;

    @PostConstruct
    public void postConstruct() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(this.messages);
    }

}
