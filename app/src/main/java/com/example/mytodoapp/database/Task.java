package com.example.mytodoapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String title;
    private final boolean isComplete;
    private final int priority;
    @ColumnInfo(name="updated_at")
    private String updatedAt;
    @ColumnInfo(name = "notify_time")
    private String notifyTime;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Task(String title, String updatedAt, String notifyTime, boolean isComplete, int priority) {
        this.title = title;
        this.updatedAt = updatedAt;
        this.notifyTime = notifyTime;
        this.isComplete = isComplete;
        this.priority = priority;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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
