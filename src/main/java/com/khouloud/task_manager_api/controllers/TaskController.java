// VERSION AVANCÉE - Avec DTO mapper et ResponseEntity générique
package com.khouloud.task_manager_api.controllers;

import com.khouloud.task_manager_api.dto.TaskRequest;
import com.khouloud.task_manager_api.dto.TaskResponse;
import com.khouloud.task_manager_api.exception.ResourceNotFoundException;
import com.khouloud.task_manager_api.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // PUT update task - Version AVANCÉE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest taskRequest) {
        
        try {
            // Essayer de mettre à jour la tâche
            TaskResponse updatedTask = taskService.updateTask(id, taskRequest);
            
            // Retourner la réponse avec la tâche mise à jour
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Tâche mise à jour avec succès");
            response.put("data", updatedTask);
            response.put("timestamp", System.currentTimeMillis());
            
            return ResponseEntity.ok(response);
            
        } catch (ResourceNotFoundException e) {
            // Tâche non trouvée
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("errorCode", "TASK_NOT_FOUND");
            errorResponse.put("timestamp", System.currentTimeMillis());
            
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
            
        } catch (IllegalArgumentException e) {
            // Données invalides
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("errorCode", "INVALID_DATA");
            errorResponse.put("timestamp", System.currentTimeMillis());
            
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
            
        } catch (Exception e) {
            // Erreur serveur inattendue
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Erreur interne du serveur");
            errorResponse.put("errorCode", "INTERNAL_SERVER_ERROR");
            errorResponse.put("details", e.getMessage());
            errorResponse.put("timestamp", System.currentTimeMillis());
            
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
        }
    }
}