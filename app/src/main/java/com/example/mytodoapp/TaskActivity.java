package com.example.mytodoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytodoapp.data.Task;
import com.example.mytodoapp.data.TaskRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TaskActivity extends AppCompatActivity {

    public static final int ADD_TASK_REQUEST = 1;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        FloatingActionButton buttonAddTask = findViewById(R.id.button_add_task);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, TaskDetailActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TaskAdapter adapter = new TaskAdapter(this);
        recyclerView.setAdapter(adapter);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAlltasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });
    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(TaskDetailActivity.EXTRA_TITLE);
            String description = data.getStringExtra(TaskDetailActivity.EXTRA_DESCRIPTION);
            boolean isCompl = data.getBooleanExtra(TaskDetailActivity.EXTRA_COMPLETE,false);
            int priority = data.getIntExtra(TaskDetailActivity.EXTRA_PRIORITY,1);

            Task task = new Task(title,description,isCompl,priority);
            taskViewModel.insert(task);

            Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Task Not Saved",Toast.LENGTH_SHORT).show();
        }
    }
}