package output;

import java.util.List;

public class MonthlyStatsOutput {
    private final int month;
    private final List<Integer> distributorsIds;

    public MonthlyStatsOutput(int month, List<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public int getMonth() {
        return month;
    }

    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }
}
