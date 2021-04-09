package output;

import java.util.List;
/**
 * Clasa care contine datele despre un distribuitor
 * care vor fi scrise in fisierul de iesire in format JSON
 */
public final class DistributorOutput {

    private final int id;
    private final int energyNeededKW;
    private final int contractCost;
    private final int budget;
    private final String producerStrategy;
    private final boolean isBankrupt;
    private final List<ContractOutput> contracts;

    public DistributorOutput(int id, int energyNeededKW, int contractCost,
                             int budget, String producerStrategy, boolean isBankrupt,
                             List<ContractOutput> contracts) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public int getBudget() {
        return budget;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public List<ContractOutput> getContracts() {
        return contracts;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }
}
