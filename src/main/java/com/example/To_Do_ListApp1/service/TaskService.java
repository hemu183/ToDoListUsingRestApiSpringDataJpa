package com.example.To_Do_ListApp1.service;

import com.example.To_Do_ListApp1.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

       void addTask(Task task);

        List<Task> getAllTask();

        Task deleteTask(Task obj);

        Task updateTaskById(int id, Task task);

        void updateTask(Task obj);

        void deleteTaskById(int id);

        String loaded();

}
