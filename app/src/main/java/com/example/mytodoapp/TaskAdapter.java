package com.example.mytodoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodoapp.data.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private List<Task> tasks = new ArrayList<>();
    private final Context mContext;

    public TaskAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_item, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        final Task CurrentTask = tasks.get(position);
        holder.textViewTitle.setText(String.valueOf(CurrentTask.getTitle()));
        holder.textViewDesc.setText(String.valueOf(CurrentTask.getDescription()));
        holder.textViewPriority.setText(String.valueOf(CurrentTask.getPriority()));
        holder.textViewComplete.setChecked(CurrentTask.isComplete());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }
       static class TaskHolder extends RecyclerView.ViewHolder{
       private final TextView textViewTitle;
       private final TextView textViewDesc;
       private final TextView textViewPriority;
       private final CheckBox textViewComplete;
       public TaskHolder(View itemView){
           super(itemView);
           textViewTitle = itemView.findViewById(R.id.text_title);
           textViewDesc = itemView.findViewById(R.id.text_description);
           textViewPriority = itemView.findViewById(R.id.text_priority);
           textViewComplete = itemView.findViewById(R.id.text_complete);
       }
    }
}
