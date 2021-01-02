package com.example.mytodoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mytodoapp.data.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class TaskActivity extends AppCompatActivity {
    public static final int ADD_TASK_REQUEST = 1;
    public static final int EDIT_TASK_REQUEST = 2;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        FloatingActionButton buttonAddTask = findViewById(R.id.button_add_task);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, AddEditTaskActivity.class);
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
                adapter.submitList(tasks);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskViewModel.delete(adapter.getTaskAt(viewHolder.getAdapterPosition()));
                Toast.makeText(TaskActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                Intent intent = new Intent(TaskActivity.this, AddEditTaskActivity.class);
                intent.putExtra(AddEditTaskActivity.EXTRA_ID,task.getId());
                intent.putExtra(AddEditTaskActivity.EXTRA_TITLE,task.getTitle());
                intent.putExtra(AddEditTaskActivity.EXTRA_DATE,task.getUpdatedAt());
                intent.putExtra(AddEditTaskActivity.EXTRA_COMPLETE,task.isComplete());
                intent.putExtra(AddEditTaskActivity.EXTRA_PRIORITY,task.getPriority());
                startActivityForResult(intent, EDIT_TASK_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddEditTaskActivity.EXTRA_TITLE);
            Date date = new Date();
            boolean isCompl = data.getBooleanExtra(AddEditTaskActivity.EXTRA_COMPLETE,false);
            int priority = data.getIntExtra(AddEditTaskActivity.EXTRA_PRIORITY,1);

            Task task = new Task(title,date,isCompl,priority);
            taskViewModel.insert(task);

            Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_TASK_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditTaskActivity.EXTRA_ID, -1);

            if(id == -1){
                Toast.makeText(this, "Task Can't be Updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditTaskActivity.EXTRA_TITLE);
            Date date = new Date();
            boolean isCompl = data.getBooleanExtra(AddEditTaskActivity.EXTRA_COMPLETE,false);
            int priority = data.getIntExtra(AddEditTaskActivity.EXTRA_PRIORITY,1);

            Task task = new Task(title,date,isCompl,priority);
            task.setId(id);
            taskViewModel.update(task);
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this,"Task Not Saved",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        boolean isComplete = intent.getBooleanExtra(AddEditTaskActivity.EXTRA_COMPLETE,false);
        switch (item.getItemId()){
            case R.id.delete_all_notes:
                taskViewModel.deleteCompletedTask(isComplete);
                Toast.makeText(this, "Completed Task Deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}