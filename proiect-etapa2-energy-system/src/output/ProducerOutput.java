package output;

import java.util.List;

public final class ProducerOutput {

    private final int id;
    private final int maxDistributors;
    private final double priceKW;
    private final String energyType;
    private final int energyPerDistributor;
    private List<MonthlyStatsOutput> monthlyStats;

    public ProducerOutput(final int id, final int maxDistributors,
                          final double priceKW, final String energyType,
                          final int energyPerDistributor,
                          final List<MonthlyStatsOutput> monthlyStats) {

        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public int getId() {
        return id;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public String getEnergyType() {
        return energyType;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public List<MonthlyStatsOutput> getMonthlyStats() {
        return monthlyStats;
    }
}
