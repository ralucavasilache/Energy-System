package input;

import java.util.List;
/**
 * Clasa care contine update-urile lunare,
 * parsate din format JSON
 */
public final class MonthlyUpdate {

    private final List<ConsumerData> newConsumers;
    private final List<DistributorChanges> distributorChanges;
    private final List<ProducerChanges> producerChanges;


    public MonthlyUpdate() {

        this.newConsumers = null;
        this.distributorChanges = null;
        this.producerChanges = null;
    }

    public List<ConsumerData> getNewConsumers() {
        return newConsumers;
    }

    public List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }
}
