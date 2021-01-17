package com.example.CRUDdemo.Repositories;

import com.example.CRUDdemo.Models.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository <Task, Long> {
    List<Task> findAllByEnabled(boolean isEnabled);
    Task findByTitle(String title);
}
