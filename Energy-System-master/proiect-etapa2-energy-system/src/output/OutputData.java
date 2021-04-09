package output;

import java.util.List;
/**
 * Clasa care contine listele de consumatori si distribuitori
 * care vor fi scrise in fisierul de iesire in format JSON
 */
public final class OutputData {

    private final List<ConsumerOutput> consumers;
    private final List<DistributorOutput> distributors;
    private final List<ProducerOutput> energyProducers;

    public OutputData(List<ConsumerOutput> consumers, List<DistributorOutput> distributors,
                      List<ProducerOutput> energyProducers) {
        this.consumers = consumers;
        this.distributors = distributors;
        this.energyProducers = energyProducers;
    }

    public List<ConsumerOutput> getConsumers() {
        return consumers;
    }

    public List<DistributorOutput> getDistributors() {
        return distributors;
    }

    public List<ProducerOutput> getEnergyProducers() {
        return energyProducers;
    }
}
