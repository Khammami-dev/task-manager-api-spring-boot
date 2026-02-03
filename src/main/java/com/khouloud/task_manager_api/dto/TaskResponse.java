package com.khouloud.task_manager_api.dto;

import com.khouloud.task_manager_api.models.Task;
import com.khouloud.task_manager_api.models.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Priority priority;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Mapper statique pour convertir Task → TaskResponse
    public static TaskResponse fromEntity(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.isCompleted(),
            task.getPriority(),
            task.getCategory(),
            task.getCreatedAt(),
            task.getUpdatedAt()
        );
    }
}
