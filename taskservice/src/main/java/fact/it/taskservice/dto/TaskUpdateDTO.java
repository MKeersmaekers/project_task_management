package fact.it.taskservice.dto;

import lombok.Data;

@Data
public class TaskUpdateDTO {
    private String name;
    private String description;
    private Boolean completed;
}