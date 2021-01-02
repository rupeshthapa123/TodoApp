package com.example.mytodoapp.data;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mytodoapp.TaskDao;
import com.example.mytodoapp.TaskDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> alltasks;

    public TaskRepository(Application application){
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
        alltasks = taskDao.getAllTasks();
    }

    public void insert(final Task task){
        TaskDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run() {
                taskDao.insert(task);
            }
        });
    }

    public void update(final Task task){
         TaskDatabase.databaseWriteExecutor.execute(new Runnable() {
             @Override
             public void run() {
                 taskDao.update(task);
             }
         });
    }

    public void delete(final Task task){
          TaskDatabase.databaseWriteExecutor.execute(new Runnable() {
              @Override
              public void run() {
                  taskDao.delete(task);
              }
          });
    }

    public void deleteCompletedTask(final Boolean value){
        TaskDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.deleteCompletedTask(value);
            }
        });
    }

    public LiveData<List<Task>> getAlltasks() {
        return alltasks;
    }

    public LiveData<Task> getTaskById(int taskId){
        return taskDao.loadTAskById(taskId);
    }
}


