package com.example.To_Do.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.To_Do.Model.ToDoModel;
import com.example.To_Do.Repo.ToDoRepo;

@Service
public class ToDoService {

    @Autowired
    ToDoRepo repo;

    public List<ToDoModel> getAllToDoItems() {
        ArrayList<ToDoModel> todoList = new ArrayList<>();
        repo.findAll().forEach(todo -> todoList.add(todo));
        return todoList;
    }

    public ToDoModel getToDoItemById(Long Id) {
        return repo.findById(Id).get();
    }

    public boolean saveOrUpdateToDoItem(ToDoModel todo) {
        ToDoModel updatedObj = repo.save(todo);

        if (getToDoItemById(updatedObj.getId()) != null) {
            return true;
        }

        return false;
    }

    public ToDoModel updateStatus(Long Id) {
        ToDoModel toDoModel2 = repo.findById(Id).get();
        toDoModel2.setStatus("Completed");

        return repo.save(toDoModel2);
    }

    public boolean deleteToDoItem(Long id) {
        repo.deleteById(id);
        System.out.println("jhgdjsfkjds"  +id);
        if (repo.findById(id).isEmpty()) {
            return true;
        }
        return false;
    }

    @Scheduled(cron = "0 0 * * * ?") // Every hour(added)for check
    public void performScheduledTask() {
        // Your scheduled task logic here
        System.out.println("Scheduled task running at " + new java.util.Date());
        // Example: print all to-do items
        List<ToDoModel> allToDoItems = getAllToDoItems();
        allToDoItems.forEach(item -> System.out.println(item));
    }
}
