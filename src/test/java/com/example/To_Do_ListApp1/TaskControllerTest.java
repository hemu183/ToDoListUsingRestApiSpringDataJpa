package com.example.To_Do_ListApp1;
import com.example.To_Do_ListApp1.APIResponse.WeatherResponse;
import com.example.To_Do_ListApp1.DTO.DashboardResponse;
import com.example.To_Do_ListApp1.controller.TaskController;
import com.example.To_Do_ListApp1.model.Task;
import com.example.To_Do_ListApp1.service.TaskService;
import com.example.To_Do_ListApp1.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    class TaskControllerTest {

        @Mock
        private TaskService taskService;

        @Mock
        private WeatherService weatherService;

        @InjectMocks
        private TaskController taskController;

        @Test
        void testAddTask() {

            Task task = new Task(
                    1,
                    "Learn Spring Boot",
                    "REST APIs",
                    "PENDING",
                    null,
                    null
            );

            String result =
                    taskController.addTask(task);

            assertEquals(
                    "Task Added Successfully",
                    result
            );

            verify(taskService, times(1))
                    .addTask(task);
        }

        @Test
        void testGetDashboard() {

            List<Task> tasks = Arrays.asList(

                    new Task(
                            1,
                            "Learn Spring Boot",
                            "REST APIs",
                            "PENDING",
                            null,
                            null
                    )
            );

            WeatherResponse weather =
                    new WeatherResponse();

            when(taskService.getAllTask())
                    .thenReturn(tasks);

            when(weatherService.getWeatherDetails("Hyderabad"))
                    .thenReturn(weather);

            DashboardResponse response =
                    taskController.getDashboard();

            assertEquals(
                    tasks,
                    response.getTasks()
            );

            assertEquals(
                    weather,
                    response.getWeather()
            );

            verify(taskService, times(1))
                    .getAllTask();

            verify(weatherService, times(1))
                    .getWeatherDetails("Hyderabad");
        }

        @Test
        void testDeleteTask() {

            Task task = new Task(
                    1,
                    "Learn Spring Boot",
                    "REST APIs",
                    "PENDING",
                    null,
                    null
            );

            when(taskService.deleteTask(task))
                    .thenReturn(task);

            Task result =
                    taskController.deleteTask(task);

            assertEquals(
                    task,
                    result
            );

            verify(taskService, times(1))
                    .deleteTask(task);
        }

        @Test
        void testDeleteTaskById() {

            when(taskService.deleteTaskById(1))
                    .thenReturn("Task deleted successfully");

            String result =
                    taskController.deleteTask(1);

            assertEquals(
                    "Task deleted successfully",
                    result
            );

            verify(taskService, times(1))
                    .deleteTaskById(1);
        }

        @Test
        void testUpdateStatus() {

            Task task = new Task(
                    1,
                    "Learn Spring Boot",
                    "REST APIs",
                    "COMPLETED",
                    null,
                    null
            );

            String result =
                    taskController.updateStatus(task);

            assertEquals(
                    "Updated Successfully",
                    result
            );

            verify(taskService, times(1))
                    .updateTask(task);
        }

        @Test
        void testUpdateTask() {

            Task task = new Task(
                    1,
                    "Updated Title",
                    "Updated Description",
                    "COMPLETED",
                    null,
                    null
            );

            when(taskService.updateTaskById(1, task))
                    .thenReturn(task);

            Task result =
                    taskController.updateTask(1, task);

            assertEquals(
                    task,
                    result
            );

            verify(taskService, times(1))
                    .updateTaskById(1, task);
        }

        @Test
        void testCreateTasks() {

            when(taskService.loaded())
                    .thenReturn("Loaded");

            String result =
                    taskController.createtasks();

            assertEquals(
                    "Loaded",
                    result
            );

            verify(taskService, times(1))
                    .loaded();
        }
    }

