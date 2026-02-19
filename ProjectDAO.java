package dao;

import model.Project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/project_scheduler";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Anj@li183109";  // change this

    public void addProject(String title, int deadline, int revenue) throws Exception {
        String sql = "INSERT INTO projects (title, deadline, revenue) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setInt(2, deadline);
            ps.setInt(3, revenue);
            ps.executeUpdate();
        }
    }

    public List<Project> getAllProjects() throws Exception {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT * FROM projects ORDER BY id";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Project p = new Project(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("deadline"),
                        rs.getInt("revenue")
                );
                list.add(p);
            }
        }
        return list;
    }
}
