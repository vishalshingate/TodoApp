package com.example.todoapi.Contollers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;



@Service
public class TodoService1 implements FakeTodoService {
    @Override
    public String doSomething() {
        return "doSomething";
    }
}
