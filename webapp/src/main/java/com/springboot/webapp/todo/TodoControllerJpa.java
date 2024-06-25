package com.springboot.webapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	@Autowired
	private TodoRepo todoRepo;

	public TodoControllerJpa( TodoRepo todoRepo) {
		super();
		this.todoRepo = todoRepo;
	}

	@RequestMapping("TodoList")
	public String listAllTodo(ModelMap model) {
		List<Todo> listOfTodo = todoRepo.findByUsername(getLoggerinUsername());
		model.addAttribute("todos", listOfTodo);
		return "listTodo";
	}
	
	@GetMapping("Todo")
	public String showNewTodoPage(ModelMap model) {
		Todo todo = new Todo(0,(String)model.get("name")," ",LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "Todo";
	} 
	
	@PostMapping("Todo")
	public String addNewTodoPage(@Valid Todo todo,ModelMap model, BindingResult results) {
		if(results.hasErrors()) {
			return "Todo";
		}
		todo.setUsername(this.getLoggerinUsername());
		todoRepo.save(todo);
//		todoService.addTodo(, todo.getDescription(), todo.getDate()  , todo.isDone());
		return "redirect:TodoList";
	} 
	
	@RequestMapping("delete-todo")
	public String deleteTodoPage(@RequestParam int id) {
		todoRepo.deleteById(id);
	return "redirect:TodoList";
	} 
	@GetMapping("update-todo")
	public String updateTodoPage(@RequestParam int id,ModelMap model) {
		Todo todo = todoRepo.findById(id).get();
		model.addAttribute("todo", todo);
	return "Todo";
	} 
	
	@PostMapping("update-todo")
	public String updateTodo(@Valid Todo todo,ModelMap model, BindingResult results) {
		if(results.hasErrors()) {
			return "Todo";
		}
		todo.setUsername(this.getLoggerinUsername());
		todoRepo.save(todo);
//		todoService.updateTodo(todo);
		return "redirect:TodoList";
	} 
	
	private String getLoggerinUsername() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
		}
}
