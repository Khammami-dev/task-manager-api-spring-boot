package com.khouloud.task_manager_api.repository;

import com.khouloud.task_manager_api.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.khouloud.task_manager_api.models.Priority;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Méthodes de recherche personnalisées
    List<Task> findByCompleted(boolean completed);
    
    List<Task> findByPriority(Priority priority);
    
    List<Task> findByCategory(String category);
    
    List<Task> findByTitleContainingIgnoreCase(String keyword);
    
    // Recherche combinée
    List<Task> findByCompletedAndPriority(boolean completed, Priority priority);
}