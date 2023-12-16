package fact.it.taskservice;

import fact.it.taskservice.dto.TaskDTO;
import fact.it.taskservice.dto.TaskUpdateDTO;
import fact.it.taskservice.model.Task;
import fact.it.taskservice.repository.TaskRepository;
import fact.it.taskservice.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaskserviceUnitTests {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        Task task = new Task(1L, "ABC123", "Task 1", "Description 1", false);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskDTO result = taskService.createTask(task);

        assertEquals(task.getSkuCode(), result.getSkuCode());
        assertEquals(task.getName(), result.getName());
        assertEquals(task.getDescription(), result.getDescription());
        assertEquals(task.isCompleted(), result.isCompleted());

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task(1L, "ABC123", "Task 1", "Description 1", false);
        Task task2 = new Task(2L, "DEF456", "Task 2", "Description 2", true);
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<TaskDTO> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        assertEquals(task1.getSkuCode(), result.get(0).getSkuCode());
        assertEquals(task2.getName(), result.get(1).getName());

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testGetTask() {
        Long taskId = 1L;
        Task task = new Task(taskId, "ABC123", "Task 1", "Description 1", false);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        TaskDTO result = taskService.getTask(taskId);

        assertEquals(task.getSkuCode(), result.getSkuCode());
        assertEquals(task.getName(), result.getName());
        assertEquals(task.getDescription(), result.getDescription());
        assertEquals(task.isCompleted(), result.isCompleted());

        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void testGetTask_NotFound() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.getTask(taskId));

        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;
        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void testUpdateTask() {
        Long taskId = 1L;
        TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO();
        taskUpdateDTO.setName("Updated Task");
        taskUpdateDTO.setDescription("Updated Description");
        taskUpdateDTO.setCompleted(true);

        Task existingTask = new Task(taskId, "ABC123", "Task 1", "Description 1", false);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(existingTask);

        TaskDTO result = taskService.updateTask(taskId, taskUpdateDTO);

        assertEquals(existingTask.getSkuCode(), result.getSkuCode());
        assertEquals(taskUpdateDTO.getName(), result.getName());
        assertEquals(taskUpdateDTO.getDescription(), result.getDescription());
        assertEquals(taskUpdateDTO.getCompleted(), result.isCompleted());

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(existingTask);
    }
}
