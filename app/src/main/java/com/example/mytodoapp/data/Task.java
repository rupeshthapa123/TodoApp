package com.example.mytodoapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private boolean isComplete;
    private int priority;

    public Task(String title, String description, boolean isComplete, int priority) {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
        this.priority = priority;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }
    public int getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }
}
