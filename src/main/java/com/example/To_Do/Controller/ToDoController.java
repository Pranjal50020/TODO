package com.example.To_Do.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.To_Do.Model.ToDoModel;
import com.example.To_Do.Service.ToDoService;

@Controller
public class ToDoController {

    @Autowired
    private ToDoService todoservice;

    @GetMapping({"/viewToDoList"})
    public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", todoservice.getAllToDoItems());
        model.addAttribute("msg", message);
        return "ViewToDoList";
    }

    @GetMapping("/viewToDoList/updateToDoStatus/{id}")
    public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (todoservice.updateStatus(id) != null) {
            redirectAttributes.addFlashAttribute("message", "Update Success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Update Failure");
        }
        return "redirect:/viewToDoList";
    }

    @GetMapping("/viewToDoList/addToDoItem")
    public String addToDoItems(Model model) {
        model.addAttribute("todoModel", new ToDoModel());
        return "AddToDoItem";
    }

    @PostMapping("/viewToDoList/saveToDoItem")
    public String saveToDoItem(@ModelAttribute("todoModel") ToDoModel todo, RedirectAttributes redirectAttributes) {
        if (todoservice.saveOrUpdateToDoItem(todo)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/viewToDoList/addToDoItem";
    }

    @GetMapping("/viewToDoList/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Long id, Model model) {
        ToDoModel todo = todoservice.getToDoItemById(id);
        model.addAttribute("todo", todo);
        return "EditToDoItem";
    }

    @PostMapping("/viewToDoList/editSaveToDoItem")
    public String editSaveToDoItem(@ModelAttribute("todo") ToDoModel todo, RedirectAttributes redirectAttributes) {
        if (todoservice.saveOrUpdateToDoItem(todo)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/viewToDoList/editToDoItem/" + todo.getId();
    }

    

    @GetMapping("/viewToDoList/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (todoservice.deleteToDoItem(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Delete Failure");
        }
        return "redirect:/viewToDoList";
    }
}
