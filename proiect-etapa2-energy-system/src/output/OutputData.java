package output;

import java.util.List;
/**
 * Clasa care contine listele de consumatori si distribuitori
 * care vor fi scrise in fisierul de iesire in format JSON
 */
public final class OutputData {

    private final List<ConsumerOutput> consumers;
    private final List<DistributorOutput> distributors;

    public OutputData(final List<ConsumerOutput> consumers,
                      final List<DistributorOutput> distributors) {

        this.consumers = consumers;
        this.distributors = distributors;
    }

    public List<ConsumerOutput> getConsumers() {
        return consumers;
    }

    public List<DistributorOutput> getDistributors() {
        return distributors;
    }
}
