package main;

import dao.ProjectDAO;
import model.Project;
import service.SchedulerService;

import java.util.List;

public class ScheduleTest {
    public static void main(String[] args) {
        try {
            ProjectDAO dao = new ProjectDAO();
            List<Project> allProjects = dao.getAllProjects();

            List<Project> schedule = SchedulerService.generateSchedule(allProjects);

            System.out.println("📅 Optimal Weekly Schedule (Mon–Fri):");
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

            for (int i = 0; i < schedule.size(); i++) {
                Project p = schedule.get(i);
                System.out.println(days[i] + " -> " + p.getTitle()
                        + " (Deadline: " + p.getDeadline()
                        + ", Revenue: ₹" + p.getRevenue() + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
