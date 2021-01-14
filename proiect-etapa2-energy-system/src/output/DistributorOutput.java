package output;

import java.util.List;
/**
 * Clasa care contine datele despre un distribuitor
 * care vor fi scrise in fisierul de iesire in format JSON
 */
public final class DistributorOutput {

    private final int id;
    private final int budget;
    private final boolean isBankrupt;
    private final List<ContractOutput> contracts;

    public DistributorOutput(final int id, final int budget, final boolean isBankrupt,
                             final List<ContractOutput> contracts) {

        this.id = id;
        this.budget = budget;
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
}
