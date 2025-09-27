package com.example.TodoApiSpring;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {


    private TodoService todoService;
    private TodoService todoService1;
private static List<Todo> todoList;
private static final String TODO_NOT_FOUND = "Todo not found";

public TodoController(@Qualifier("fakeTodoService") TodoService todoService, @Qualifier("anotherTodoService") TodoService todoService1){
    this.todoService = todoService ;
    this.todoService1 = todoService1 ;
    todoList  = new ArrayList<>() ;
    todoList.add(new Todo(1, false, "Todo 1", 1) );
    todoList.add(new Todo(2, true, "Todo 2", 2) );
}

@GetMapping
    public ResponseEntity <List<Todo>> getTodos(@RequestParam(required = false) boolean  isCompleted){
     System.out.println("Incoming Query params : "+ isCompleted);
     System.out.println(todoService1.doSomething());
    return ResponseEntity.status(HttpStatus.OK).body(todoList);
}

  @PostMapping
  //@ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity <Todo> createTodo(@RequestBody Todo newTodo){
    todoList.add(newTodo);
    return  ResponseEntity.status(HttpStatus.CREATED).body(newTodo) ;
}

 @GetMapping("/{todoId}")
    public ResponseEntity<? > getTodoById(@PathVariable Long todoId){

    for(Todo todo: todoList){
        if(todo.getId()== todoId){
            return ResponseEntity.ok(todo);
        }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
 }
}
