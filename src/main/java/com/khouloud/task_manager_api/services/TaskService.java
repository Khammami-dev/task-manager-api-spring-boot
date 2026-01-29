package com.khouloud.task_manager_api.services;

import com.khouloud.task_manager_api.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Commence à 1

    public TaskService() {
        // Ajout de tâches exemple
        createTask(new Task(idCounter.getAndIncrement(), "Apprendre Spring Boot", "Créer ma première API REST"));
        createTask(new Task(idCounter.getAndIncrement(), "Acheter du lait", "Aller au supermarché"));
    }

    // GET ALL
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks); // Retourne une copie
    }

    // GET BY ID
    public Optional<Task> getTaskById(Long id) {
        return tasks.stream()
                   .filter(task -> task.getId().equals(id))
                   .findFirst();
    }

    // CREATE
    public Task createTask(Task task) {
        task.setId(idCounter.getAndIncrement());
        tasks.add(task);
        return task;
    }

    // UPDATE
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return getTaskById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
            return task;
        });
    }

    // DELETE
    public boolean deleteTask(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
    
}
