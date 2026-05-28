package com.example.To_Do_ListApp1.controller;

import com.example.To_Do_ListApp1.APIResponse.WeatherResponse;
import com.example.To_Do_ListApp1.DTO.DashboardResponse;
import com.example.To_Do_ListApp1.model.Task;
import com.example.To_Do_ListApp1.service.TaskService;
import com.example.To_Do_ListApp1.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    WeatherService weatherService;



    // Adding a task
    @PostMapping("Task")
    public String addTask(@RequestBody Task task){
        taskService.addTask(task);
        return "Task Added Successfully";
    }


    // View all tasks along with weather details
    @GetMapping("dashboard")
    public DashboardResponse getDashboard(){

        List<Task> tasks = taskService.getAllTask();

        WeatherResponse weather =
                weatherService.getWeatherDetails("Hyderabad");

        DashboardResponse response = new DashboardResponse();

        response.setTasks(tasks);

        response.setWeather(weather);

        return response;
    }


    //Deleting a Task by task Object
    @DeleteMapping("Task")
    public Task deleteTask(@RequestBody Task obj){
       return taskService.deleteTask(obj);
    }


    //Deleting a Task by id
    @DeleteMapping("Task/{id}")
    public String deleteTask(@PathVariable  int id){
        return taskService.deleteTaskById(id);

    }


    // Updating status parameter
    @PatchMapping("Task")
    public String updateStatus(@RequestBody Task obj){
        taskService.updateTask(obj);
        return "Updated Successfully";
    }

    //Replace or update entire task
    @PutMapping("Task/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task){
        return taskService.updateTaskById(id, task);
    }


    // Loading tasks
    @PostMapping("Load")
    public String createtasks(){
       return taskService.loaded();
    }


}


