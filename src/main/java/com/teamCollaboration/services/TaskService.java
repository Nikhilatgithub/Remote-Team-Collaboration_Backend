package com.teamCollaboration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.entities.Task;
import com.teamCollaboration.repository.TaskRepository;

@Service
public class TaskService {
	private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Method to save or update a task
    public Task saveOrUpdateTask(Task task) {
        return taskRepository.save(task);
    }

    // Method to retrieve all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Method to find task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Method to delete task by ID
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
