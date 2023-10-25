package fact.it.taskservice.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private String name;
    private String description;
    private boolean completed;
}
