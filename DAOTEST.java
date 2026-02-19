package main;

import dao.ProjectDAO;
import model.Project;
import java.util.List;

public class DAOTEST {
    public static void main(String[] args) {
        try {
            ProjectDAO dao = new ProjectDAO();

            // Insert test project
            dao.addProject("UI Design", 2, 4000);
            dao.addProject("API Development", 3, 6000);

            // Fetch & print projects
            List<Project> projects = dao.getAllProjects();
            System.out.println("📋 Projects in Database:");
            for (Project p : projects) {
                System.out.println(
                        p.getId() + " | " +
                                p.getTitle() + " | Deadline: " +
                                p.getDeadline() + " | Revenue: " +
                                p.getRevenue()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
