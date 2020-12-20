package com.example.mytodoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodoapp.data.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private List<Task> tasks = new ArrayList<>();
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task CurrentTask = tasks.get(position);
        holder.textViewTitle.setText(CurrentTask.getTitle());
        holder.textViewDesc.setText(CurrentTask.getDescription());
        holder.textViewPriority.setText(CurrentTask.getPriority());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    class TaskHolder extends RecyclerView.ViewHolder{
       private TextView textViewTitle;
       private TextView textViewDesc;
       private TextView textViewPriority;

       public TaskHolder(View itemView){
           super(itemView);
           textViewTitle = itemView.findViewById(R.id.text_title);
           textViewDesc = itemView.findViewById(R.id.text_description);
           textViewPriority = itemView.findViewById(R.id.text_priority);;
       }
    }
}
