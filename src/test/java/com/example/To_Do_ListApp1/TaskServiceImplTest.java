package com.example.To_Do_ListApp1;

import com.example.To_Do_ListApp1.APIResponse.AIResponse;
import com.example.To_Do_ListApp1.model.Task;
import com.example.To_Do_ListApp1.repository.TaskRepository;
import com.example.To_Do_ListApp1.service.AIService;
import com.example.To_Do_ListApp1.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private AIService aiService;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Mock
    private TaskRepository taskRepository;



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

        Task deletedTask =
                taskServiceImpl.deleteTask(task);

        assertEquals(task, deletedTask);

        verify(taskRepository, times(1))
                .delete(task);
    }

    @Test
    void testUpdateTask() {

        Task existingTask = new Task(
                1,
                "Learn Spring Boot",
                "REST APIs",
                "PENDING",
                null,
                null
        );

        Task updatedTask = new Task(
                1,
                null,
                null,
                "COMPLETED",
                null,
                null
        );

        when(taskRepository.findById(1))
                .thenReturn(Optional.of(existingTask));

        taskServiceImpl.updateTask(updatedTask);

        assertEquals(
                "COMPLETED",
                existingTask.getStatus()
        );

        verify(taskRepository, times(1))
                .findById(1);

        verify(taskRepository, times(1))
                .save(existingTask);
    }

    @Test
    void testDeleteTaskById() {

        taskServiceImpl.deleteTaskById(1);

        verify(taskRepository, times(1))
                .deleteById(1);
    }

    @Test
    void testUpdateTaskById() {

        Task existingTask = new Task(
                1,
                "Old Title",
                "Old Description",
                "PENDING",
                null,
                null
        );

        Task updatedTask = new Task(
                1,
                "New Title",
                "New Description",
                "COMPLETED",
                null,
                null
        );

        when(taskRepository.findById(1))
                .thenReturn(Optional.of(existingTask));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(existingTask);

        Task result =
                taskServiceImpl.updateTaskById(1, updatedTask);

        assertEquals(
                "New Title",
                result.getTitle()
        );

        assertEquals(
                "New Description",
                result.getDescription()
        );

        assertEquals(
                "COMPLETED",
                result.getStatus()
        );

        verify(taskRepository, times(1))
                .findById(1);

        verify(taskRepository, times(1))
                .save(existingTask);
    }

    @Test
    void testUpdateTaskById_TaskNotFound() {

        Task updatedTask = new Task(
                1,
                "New Title",
                "New Description",
                "COMPLETED",
                null,
                null
        );

        when(taskRepository.findById(1))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> taskServiceImpl.updateTaskById(1, updatedTask)
                );

        assertEquals(
                "Task not found",
                exception.getMessage()
        );
    }

    @Test
    void testLoaded() {

        String result =
                taskServiceImpl.loaded();

        assertEquals(
                "Loaded",
                result
        );

        verify(taskRepository, times(1))
                .saveAll(anyList());
    }

    @Test
    void testGetAllTask() {

        List<Task> tasks = Arrays.asList(

                new Task(
                        1,
                        "Learn Spring Boot",
                        "REST APIs",
                        "PENDING",
                        null,
                        null
                ),

                new Task(
                        2,
                        "Workout",
                        "Gym Session",
                        "COMPLETED",
                        null,
                        null
                )
        );

        when(taskRepository.findAll())
                .thenReturn(tasks);

        List<Task> result =
                taskServiceImpl.getAllTask();

        assertEquals(
                2,
                result.size()
        );

        assertEquals(
                "Learn Spring Boot",
                result.get(0).getTitle()
        );

        assertEquals(
                "Workout",
                result.get(1).getTitle()
        );

        verify(taskRepository, times(1))
                .findAll();
    }

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

        AIResponse aiResponse =
                new AIResponse(
                        "Practice daily",
                        "HIGH"
                );

        when(aiService.getAIResponse("Learn Spring Boot"))
                .thenReturn(aiResponse);

        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        taskServiceImpl.addTask(task);

        assertEquals(
                "Practice daily",
                task.getAiSuggestion()
        );

        assertEquals(
                "HIGH",
                task.getPriority()
        );

        verify(aiService, times(1))
                .getAIResponse("Learn Spring Boot");

        verify(taskRepository, times(1))
                .save(task);
    }
}



