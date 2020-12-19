package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytodoapp.data.Task;
import com.example.mytodoapp.data.TaskRepository;

import java.util.List;

public class TaskActivity extends AppCompatActivity {
/*
    private TextView title;
    private TextView description;
    private CheckBox isComplete;
    private Button next;
    Task task;
    private Button detail;
    TaskRepository repository = TaskRepository.getInstance();
*/
    private TaskViewModel taskViewModel;
    private Object ViewModelProviders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        TaskViewModel taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAlltasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                Toast.makeText(TaskActivity.this,"OnChange",Toast.LENGTH_SHORT).show();
            }
        });




         /*
        title = findViewById(R.id.title_id);
        description = findViewById(R.id.desc_id);
        isComplete = findViewById((R.id.iscom_id));
        next  =  findViewById(R.id.next_id);
        detail = findViewById(R.id.detail_id);
        task = repository.getFirstTask();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = repository.getNextTask(task);
                updateUI(task);
            }
        });
        updateUI(task);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });*/
    }
/*
    private void updateUI(Task task) {
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        isComplete.setChecked(task.isComplete());
    }*/
}