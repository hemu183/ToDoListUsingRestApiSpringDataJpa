package com.example.To_Do_ListApp1.service;

import com.example.To_Do_ListApp1.APIResponse.AIResponse;
import com.example.To_Do_ListApp1.model.Task;
import com.example.To_Do_ListApp1.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public  class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    AIService aiService;

    public void addTask(Task task) {

        AIResponse aiResponse =
                aiService.getAIResponse(task.getTitle());

        task.setAiSuggestion(
                aiResponse.getSuggestion()
        );

        task.setPriority(
                aiResponse.getPriority()
        );

        taskRepository.save(task);
    }


    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }


    public Task deleteTask(Task obj) {
         taskRepository.delete(obj);
         return obj;
    }


    public void updateTask(Task obj) {
        Optional<Task> task = taskRepository.findById(obj.getId());
        Task obj1 = task.get();
        obj1.setStatus(obj.getStatus());
        taskRepository.save(obj1);
    }


    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTaskById(int id, Task task) {

        Task existingTask =
                taskRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Task not found")
                        );

        existingTask.setTitle(
                task.getTitle()
        );

        existingTask.setDescription(
                task.getDescription()
        );

        existingTask.setStatus(
                task.getStatus()
        );

        return taskRepository.save(existingTask);
    }


    public String loaded(){
        List<Task> Tasks = new ArrayList<>(Arrays.asList(

                new Task(1, "Buy groceries", "Milk", "PENDING",null,null),

                new Task(2, "Finish project", "Complete the Spring Boot backend module", "IN_PROGRESS",null,null),

                new Task(3, "Workout", "Evening gym session", "COMPLETED",null,null),

                new Task(4, "Pay bills", "Electricity and Internet bills", "PENDING",null,null),

                new Task(5, "Call mom", "Weekend family catch-up", "PENDING",null,null)));

        taskRepository.saveAll(Tasks);
        return "Loaded";

    }


}
