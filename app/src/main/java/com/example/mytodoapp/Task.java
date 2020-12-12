package com.example.mytodoapp;

public class Task {

    private int id;
    private String title;
    private String description;
    private boolean isComplete;

    public Task(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }



    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
