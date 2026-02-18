package com.promanage;

import com.promanage.dao.ProjectDao;
import com.promanage.model.Project;
import com.promanage.scheduler.ScheduleGenerator;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ProjectDao dao = new ProjectDao();

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--- ProManage Scheduler ---");
            System.out.println("1) Add Project");
            System.out.println("2) List Projects");
            System.out.println("3) Generate Weekly Schedule (Mon-Fri)");
            System.out.println("0) Exit");
            System.out.print("Select: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> addProject(sc);
                case "2" -> listProjects();
                case "3" -> generateSchedule();
                case "0" -> { System.out.println("Exiting"); return; }
                default -> System.out.println("Invalid");
            }
        }
    }

    private static void addProject(Scanner sc) {
        try {
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Deadline (working days): ");
            int deadline = Integer.parseInt(sc.nextLine());
            System.out.print("Expected revenue (integer): ");
            int revenue = Integer.parseInt(sc.nextLine());
            Project p = new Project(title, deadline, revenue);
            dao.addProject(p);
            System.out.println("Added: " + p.getId());
        } catch (Exception e) {
            System.out.println("Failed to add project: " + e.getMessage());
        }
    }

    private static void listProjects() {
        try {
            List<Project> list = dao.getAllProjects();
            System.out.println("Projects:");
            for (Project p : list) {
                System.out.printf("%s | %s | deadline=%d | revenue=%d\n", p.getId(), p.getTitle(), p.getDeadline(), p.getRevenue());
            }
        } catch (SQLException e) {
            System.out.println("Error reading projects: " + e.getMessage());
        }
    }

    private static void generateSchedule() {
        try {
            List<Project> list = dao.getAllProjects();
            var slots = ScheduleGenerator.generateSchedule(list);
            String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
            System.out.println("Weekly schedule (one project per day):");
            int total = 0;
            for (int i = 0; i < 5; i++) {
                Project p = slots[i];
                if (p != null) {
                    System.out.printf("%s: %s (rev=%d)\n", days[i], p.getTitle(), p.getRevenue());
                    total += p.getRevenue();
                } else {
                    System.out.printf("%s: (free)\n", days[i]);
                }
            }
            System.out.println("Total scheduled revenue: " + total);
        } catch (SQLException e) {
            System.out.println("Error generating schedule: " + e.getMessage());
        }
    }
}
