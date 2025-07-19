package com.example.todoapi.Contollers;


import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoControllers {
    public static List<Todo>todoList;

    //composition has a

    private FakeTodoService todoService;

    private FakeTodoService todoService2;


    public TodoControllers(@Qualifier("TodoService") FakeTodoService todoService, @Qualifier("AnotherTodoService")
                           FakeTodoService todoService2) {

        todoList=new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1",2));
        todoList.add(new Todo(2,true,"Todo 2", 2));
        this.todoService = todoService;
        this.todoService2 = todoService2;
    }

    // this will return in json/ we are not returning the json we are returning List but
    // we have annotated @RestController that why springboot is serializing it and converting it to json
//    @GetMapping("/todos")
//    public List<Todo> getTodos(){
//        return todoList;
//    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean isCompleted) {
        System.out.println(todoService.doSomething());
        System.out.println(todoService2.doSomething());
        if (isCompleted == null) return ResponseEntity.ok(todoList);

        List<Todo> filtered = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.isCompleted() == isCompleted) filtered.add(todo);
        }
        return ResponseEntity.ok(filtered);
    }




    @PostMapping("/todos")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
    todoList.add(newTodo);
    return ResponseEntity.status(HttpStatus.CREATED).body(newTodo) ;
    }

    // Path params

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<?> getTodo(@PathVariable Long todoId) {
        for(Todo todo:todoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }
        }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(todoId + "Not found");
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable Integer todoId) {
        for(Todo todo: todoList){
            if(todo.getId()== todoId) {
                todoList.remove(todo);
                return ResponseEntity.status(HttpStatus.OK).body(todoId + " Deleted");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(todoId + " Not found");

    }

    @PatchMapping("/todos/{id}")
    public ResponseEntity<?> patchTodo(@PathVariable Long id, @RequestBody Todo partialTodo) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                if (partialTodo.getTitle() != null)
                    todo.setTitle(partialTodo.getTitle());

                if (partialTodo.getUserId() !=0)
                    todo.setUserId(partialTodo.getUserId());

                todo.setCompleted(partialTodo.isCompleted()); // or use wrapper Boolean for optional

                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo with ID " + id + " not found");
    }
}
