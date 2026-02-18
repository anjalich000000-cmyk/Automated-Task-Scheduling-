package com.promanage.scheduler;

import com.promanage.model.Project;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ScheduleGenerator {
    // Days indexed 1..5 -> Monday..Friday
    public static Project[] generateSchedule(List<Project> projects) {
        Project[] slots = new Project[6]; // ignore 0

        // Sort by revenue desc
        projects.sort(Comparator.comparingInt(Project::getRevenue).reversed());

        for (Project p : projects) {
            int lastAllowed = Math.min(p.getDeadline(), 5);
            for (int d = lastAllowed; d >= 1; d--) {
                if (slots[d] == null) {
                    slots[d] = p;
                    break;
                }
            }
        }

        return Arrays.copyOfRange(slots, 1, 6);
    }
}
