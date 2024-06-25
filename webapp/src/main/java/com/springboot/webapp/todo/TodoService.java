package com.springboot.webapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
private static List<Todo> todo = new ArrayList<>();
private static int todosCount = 0;
static {
	todo.add(new Todo(++todosCount, "Bruce","liberate Gotam",LocalDate.now().plusYears(1),false));
	todo.add(new Todo(++todosCount, "Klark","save metropolis",LocalDate.now().plusYears(1),false));
}

public List<Todo> findTodoByUserName(String username){
	 Predicate<? super Todo> predicate=todo->todo.getUsername().equals(username);
	 return todo.stream().filter(predicate).toList();
}

public void addTodo(String username, String description,LocalDate date,boolean isdone) {
	Todo todoo=new Todo(++todosCount,username,description,date, isdone);
	todo.add(todoo);
}
public void deleteTodo(int id) {
	 Predicate<? super Todo> predicate=todo->todo.getId()== id;
	todo.removeIf(predicate);
}

public Todo findbyId(int id) {
	 Predicate<? super Todo> predicate=todo->todo.getId()== id;
	 return todo.stream().filter(predicate).findFirst().get();
	 }

public void updateTodo(@Valid Todo todo2) {
	deleteTodo(todo2.getId());
	todo.add(todo2);
	
}
}
