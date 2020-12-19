package com.example.mytodoapp;

import android.util.Log;

import com.example.mytodoapp.data.Task;

import java.util.ArrayList;

public class TaskRepository {

    private static TaskRepository repository;
    private ArrayList<Task> taskList = new ArrayList<>();
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
             Task task = new Task(i, "title:" + i, "description:"+i, false );
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
     }
}


