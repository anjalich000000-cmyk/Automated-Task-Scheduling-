package main;

import dao.ProjectDAO;
import model.Project;
import service.SchedulerService;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProjectDAO dao = new ProjectDAO();

        while (true) {
            System.out.println("\n==============================");
            System.out.println(" ProManage Solutions Scheduler ");
            System.out.println("==============================");
            System.out.println("1. Add Project");
            System.out.println("2. View All Projects");
            System.out.println("3. Generate Weekly Schedule");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Please enter a valid number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter project title: ");
                        String title = sc.nextLine();

                        System.out.print("Enter deadline (1 to 5): ");
                        int deadline = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter revenue: ");
                        int revenue = Integer.parseInt(sc.nextLine());

                        if (deadline < 1 || deadline > 5 || revenue <= 0) {
                            System.out.println("❌ Invalid input. Deadline must be 1–5 and revenue > 0.");
                            break;
                        }

                        dao.addProject(title, deadline, revenue);
                        System.out.println("✅ Project added successfully!");
                        break;

                    case 2:
                        List<Project> all = dao.getAllProjects();
                        System.out.println("\n📋 All Projects:");
                        if (all.isEmpty()) {
                            System.out.println("No projects found.");
                        } else {
                            for (Project p : all) {
                                System.out.println(
                                        p.getId() + " | " +
                                                p.getTitle() +
                                                " | Deadline: " + p.getDeadline() +
                                                " | Revenue: ₹" + p.getRevenue()
                                );
                            }
                        }
                        break;

                    case 3:
                        List<Project> projects = dao.getAllProjects();
                        List<Project> schedule = SchedulerService.generateSchedule(projects);

                        System.out.println("\n📅 Optimal Weekly Schedule (Mon–Fri):");
                        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

                        if (schedule.isEmpty()) {
                            System.out.println("No projects to schedule.");
                        } else {
                            for (int i = 0; i < schedule.size(); i++) {
                                Project p = schedule.get(i);
                                System.out.println(days[i] + " -> " +
                                        p.getTitle() +
                                        " (Deadline: " + p.getDeadline() +
                                        ", Revenue: ₹" + p.getRevenue() + ")");
                            }
                        }
                        break;

                    case 4:
                        System.out.println("👋 Exiting. Have a great week!");
                        System.exit(0);

                    default:
                        System.out.println("❌ Invalid choice. Try 1–4.");
                }
            } catch (Exception e) {
                System.out.println("❌ Something went wrong:");
                e.printStackTrace();
            }
        }
    }
}
