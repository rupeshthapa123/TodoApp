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

    public void insert(Task task){
        TaskDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run() {
                taskDao.insert(task);
            }
        });
    }

    public void update(Task task){
         TaskDatabase.databaseWriteExecutor.execute(new Runnable() {
             @Override
             public void run() {
                 taskDao.update(task);
             }
         });
    }

    public void delete(Task task){
          TaskDatabase.databaseWriteExecutor.execute(new Runnable() {
              @Override
              public void run() {
                  taskDao.delete(task);
              }
          });
    }

    public LiveData<List<Task>> getAlltasks() {
        return alltasks;
    }

    /*
    private static TaskRepository repository;

    public static final String TAG = "TaskRepository";

     public static TaskRepository getInstance(){
         if (repository == null){
             repository = new TaskRepository();
         }
         return repository;
     }

     private TaskRepository(){
        initTestData();
     }

     private void initTestData(){
         for (int i=0; i<5; i++){
             Task task = new Task("title:" + i, "description:" + i, false, i );
             taskList.add(task);
         }
     }
     public Task getTask(int id){
         return taskList.get(id);
     }

     public Task getNextTask(Task task) {
         int currentTaskId = task.getId();
         int nextTaskId = 0;

         if(currentTaskId<taskList.size()-1){
             nextTaskId = currentTaskId +   1;
         }
         else if (currentTaskId == taskList.size()-1){
             nextTaskId =currentTaskId;
         }
         else
         {
             Log.d(TAG, "getNextTask: currentTaskId out of bound, reset to 0!");
         }
         return taskList.get(nextTaskId);
     }

     public boolean isLast(Task nTask){
         return nTask.getId()==taskList.size()-1;
     }

     public Task getFirstTask(){
         return taskList.get(0);
     }*/
}


