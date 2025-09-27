package com.example.TodoApiSpring;

import org.springframework.stereotype.Service;

@Service("anotherTodoService")
public class AnotherTodoService implements TodoService {
    public String doSomething(){

        return "Something from another Todo Service";
    }
}
