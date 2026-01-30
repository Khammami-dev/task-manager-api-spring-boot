package com.khouloud.task_manager_api.services;


import com.khouloud.task_manager_api.models.Task;
import com.khouloud.task_manager_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.khouloud.task_manager_api.models.Priority;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    // Plus besoin de liste en mémoire ni de compteur
    
    // GET ALL
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    // GET BY ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    // CREATE
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    
    // UPDATE
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
            .map(existingTask -> {
                existingTask.setTitle(updatedTask.getTitle());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setCompleted(updatedTask.isCompleted());
                existingTask.setPriority(updatedTask.getPriority());
                existingTask.setCategory(updatedTask.getCategory());
                return taskRepository.save(existingTask);
            });
    }
    
    // DELETE
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // NOUVELLES MÉTHODES
    public List<Task> getTasksByStatus(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }
    
    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }
    
    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByTitleContainingIgnoreCase(keyword);
    }
}