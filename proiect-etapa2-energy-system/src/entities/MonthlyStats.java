package entities;

import java.util.ArrayList;
import java.util.List;

public class MonthlyStats {
    private int month;
    private List<Integer> distributorsIds;

    public MonthlyStats(int month) {
        this.month = month;
        this.distributorsIds = new ArrayList<>();
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(List<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
    public void addId(final int id) {
        distributorsIds.add(id);
    }

    @Override
    public String toString() {
        return "MonthlyStats{" +
                "month=" + month +
                ", distributorsIds=" + distributorsIds +
                '}' + "\n";
    }
}
