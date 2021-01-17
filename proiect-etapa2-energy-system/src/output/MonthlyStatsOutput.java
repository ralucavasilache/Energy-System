package output;

import java.util.List;
/**
 * Clasa care contine datele din statisticile lunare
 * care vor fi scrise in fisierul de iesire in format JSON
 */
public final class MonthlyStatsOutput {

    private final int month;
    private final List<Integer> distributorsIds;

    public MonthlyStatsOutput(final int month, final List<Integer> distributorsIds) {

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
