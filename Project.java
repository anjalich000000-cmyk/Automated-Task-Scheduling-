package model;

public class Project {

    private int id;
    public String title;
    private int deadline;
    private int duration;
    private double baseRevenue;

    public Project(int id, String title, int deadline, int duration, double baseRevenue) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.duration = duration;
        this.baseRevenue = baseRevenue;
    }

    //  Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getDuration() {
        return duration;
    }

    public double getBaseRevenue() {
        return baseRevenue;
    }

    public double getRevenue() {
        return baseRevenue;
    }
}
