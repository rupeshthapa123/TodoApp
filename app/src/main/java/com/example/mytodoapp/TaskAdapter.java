package com.example.mytodoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodoapp.data.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.TaskHolder> {
    private OnItemClickListener listener;

    private final Context mContext;
    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public TaskAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }
    private static final DiffUtil.ItemCallback<Task> DIFF_CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())&&
                    oldItem.getPriority() == newItem.getPriority()&&
                    oldItem.isComplete() == newItem.isComplete();
        }
    };

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_item, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        final Task CurrentTask = getItem(position);
        holder.textViewTitle.setText(String.valueOf(CurrentTask.getTitle()));
        holder.updatedAtView.setText(dateFormat.format(CurrentTask.getUpdatedAt()));
        holder.textViewPriority.setText(String.valueOf(CurrentTask.getPriority()));
        holder.textViewComplete.setChecked(CurrentTask.isComplete());
    }

    public Task getTaskAt(int Position){
        return getItem(Position);
    }

    class TaskHolder extends RecyclerView.ViewHolder{
       private final TextView textViewTitle;
       private final TextView updatedAtView;
       private final TextView textViewPriority;
       private final CheckBox textViewComplete;
       public TaskHolder(View itemView){
           super(itemView);
           textViewTitle = itemView.findViewById(R.id.text_title);
           updatedAtView = itemView.findViewById(R.id.taskUpdatedAt);
           textViewPriority = itemView.findViewById(R.id.text_priority);
           textViewComplete = itemView.findViewById(R.id.text_complete);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position = getAdapterPosition();
                   if (listener!=null && position !=RecyclerView.NO_POSITION ) {
                       listener.onItemClick(getItem(position));
                   }
               }
           });
       }
    }

    public interface OnItemClickListener {
        void onItemClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
