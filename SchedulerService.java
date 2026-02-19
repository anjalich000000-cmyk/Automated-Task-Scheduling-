package service;

import model.Project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SchedulerService {

    public static List<Project> generateSchedule(List<Project> projects) {
        int n = projects.size();
        int maxDays = 5;

        // dp[i][d] = max revenue using first i projects within d days
        int[][] dp = new int[n + 1][maxDays + 1];

        for (int i = 1; i <= n; i++) {
            Project p = projects.get(i - 1);
            for (int d = 1; d <= maxDays; d++) {
                // Not taking project i
                dp[i][d] = dp[i - 1][d];

                // Taking project i if deadline allows
                if (p.getDeadline() >= d) {
                    dp[i][d] = Math.max(dp[i][d], dp[i - 1][d - 1] + p.getRevenue());
                }
            }
        }

        // Backtrack to find chosen projects
        List<Project> selected = new ArrayList<>();
        int d = maxDays;

        for (int i = n; i > 0 && d > 0; i--) {
            if (dp[i][d] != dp[i - 1][d]) {
                Project p = projects.get(i - 1);
                selected.add(p);
                d--;
            }
        }

        // Reverse to keep original order (Mon -> Fri)
        Collections.reverse(selected);
        return selected;
    }
}
