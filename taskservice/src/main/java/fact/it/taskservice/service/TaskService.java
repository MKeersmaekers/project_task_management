package fact.it.taskservice.service;

import fact.it.taskservice.dto.TaskDTO;
import fact.it.taskservice.dto.TaskUpdateDTO;
import fact.it.taskservice.model.Task;
import fact.it.taskservice.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
   private final TaskRepository taskRepository;
   private final WebClient webClient;

    @Autowired
    public TaskService(TaskRepository taskRepository, WebClient webClient) {
        this.webClient = webClient;
        this.taskRepository = taskRepository;
    }

   @PostConstruct
    public void loadData() {
       if(taskRepository.count() > 0){
           Task task = new Task();
           task.setSkuCode("tube6in");
           task.setName("PHP");
           task.setDescription("work on project php");
           task.setCompleted(false);

           Task task1 = new Task();
           task.setSkuCode("dorn7kf");
           task.setName("angular");
           task.setDescription("work on project angular");
           task.setCompleted(true);

           taskRepository.save(task);
           taskRepository.save(task1);
       }
   }
    public TaskDTO createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return convertToDTO(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDTO updateTask(Long id, TaskUpdateDTO taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (taskDetails.getName() != null) {
            task.setName(taskDetails.getName());
        }
        if (taskDetails.getDescription() != null) {
            task.setDescription(taskDetails.getDescription());
        }
        if (taskDetails.getCompleted() != null) {
            task.setCompleted(taskDetails.getCompleted());
        }
        Task updatedTask = taskRepository.save(task);
        return convertToDTO(updatedTask);
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setSkuCode(task.getSkuCode());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCompleted(task.isCompleted());
        return taskDTO;
    }
}