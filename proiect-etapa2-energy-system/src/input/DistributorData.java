package input;
/**
 * Clasa care contine datele despre un distribuitor,
 * parsate din format JSON
 */
public final class DistributorData {

    private final int id;
    private final int contractLength;
    private final int initialBudget;
    private final int initialInfrastructureCost;
    private final int energyNeededKW;
    private final String producerStrategy;

    public DistributorData() {

        this.id = -1;
        this.contractLength = -1;
        this.initialBudget = -1;
        this.initialInfrastructureCost = -1;
        this.energyNeededKW = -1;
        this.producerStrategy = null;
    }

    public int getId() {
        return id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }
}
