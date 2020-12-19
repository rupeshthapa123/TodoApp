package com.example.mytodoapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mytodoapp.data.Task;
import com.example.mytodoapp.data.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel{

    private TaskRepository repository;
    private LiveData<List<Task>> alltasks;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        alltasks = repository.getAlltasks();
    }

    public void insert(Task task){
        repository.insert(task);
    }

    public void update(Task task){
        repository.update(task);
    }

    public void delete(Task task){
        repository.delete(task);
    }

    public LiveData<List<Task>> getAlltasks() {
        return alltasks;
    }
}