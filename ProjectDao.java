package com.promanage.dao;

import com.promanage.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectDao {
    public void addProject(Project p) throws SQLException {
        String sql = "INSERT INTO projects(id,title,deadline,revenue) VALUES (?,?,?,?)";
        try (Connection c = DriverManager.getConnection(DBConfig.url(), DBConfig.user(), DBConfig.password());
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, p.getId());
            ps.setString(2, p.getTitle());
            ps.setInt(3, p.getDeadline());
            ps.setInt(4, p.getRevenue());
            ps.executeUpdate();
        }
    }

    public List<Project> getAllProjects() throws SQLException {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT id,title,deadline,revenue FROM projects ORDER BY created_at";
        try (Connection c = DriverManager.getConnection(DBConfig.url(), DBConfig.user(), DBConfig.password());
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                String title = rs.getString("title");
                int deadline = rs.getInt("deadline");
                int revenue = rs.getInt("revenue");
                list.add(new Project(id, title, deadline, revenue));
            }
        }
        return list;
    }
}
