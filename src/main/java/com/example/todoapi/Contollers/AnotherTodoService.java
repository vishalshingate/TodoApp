package com.example.todoapi.Contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("AnotherTodoService")
public class AnotherTodoService implements FakeTodoService {
    //@Override


    @TimeMonitor
    public String doSomething() {
        int a= 0;
        for(int i=0;i<1000000000;i++){
             a= a+i;
        }
        return "doSomething from AnotherTodoService";
    }
}
