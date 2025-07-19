package com.example.todoapi.Contollers;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("TodoService")
public class TodoService implements FakeTodoService {
    @Override
    public String doSomething() {
        return "doSomething";
    }
}
