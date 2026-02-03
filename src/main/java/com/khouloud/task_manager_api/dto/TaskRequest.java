package com.khouloud.task_manager_api.dto;

import com.khouloud.task_manager_api.models.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.khouloud.task_manager_api.models.Priority;

@Data // Génère tous les getters, setters, equals, hashCode, toString
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    
    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 3, max = 100)
    private String title;
    
    @Size(max = 1000)
    private String description;
    
    private boolean completed;
    private Priority priority;
    private String category;
    
    // Getters et Setters
    // ...
}
