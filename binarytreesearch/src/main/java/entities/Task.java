package entities;

import java.sql.Time;

public class Task {
    private int id;
    private long start;
    private String name;
    private String content;
    private String priority;

    public Task() {
    }

    public Task(int id, long start, String name, String content, String priority) {
        this.id = id;
        this.start = start;
        this.name = name;
        this.content = content;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
