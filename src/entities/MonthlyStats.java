package entities;

import java.util.ArrayList;
import java.util.List;
/**
 Clasa care contine campurile specifice unei statistici lunare
 */
public final class MonthlyStats {

    private int month;
    private List<Integer> distributorsIds;

    public MonthlyStats(final int month) {

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

}
