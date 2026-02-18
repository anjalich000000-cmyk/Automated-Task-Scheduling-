package com.promanage.model;

import java.util.UUID;

public class Project {
    private UUID id;
    private String title;
    private int deadline; // working days
    private int revenue;

    public Project(String title, int deadline, int revenue) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.deadline = deadline;
        this.revenue = revenue;
    }

    public Project(UUID id, String title, int deadline, int revenue) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.revenue = revenue;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public int getDeadline() { return deadline; }
    public int getRevenue() { return revenue; }
}
