package input;

import java.util.List;
/**
 * Clasa care contine listele de consumatori si de distribuitori,
 * parsate din format JSON
 */
public final class InitialData {

    private final List<ConsumerData> consumers;
    private final List<DistributorData> distributors;
    private final List<ProducerData> producers;


    public InitialData() {

        this.consumers = null;
        this.distributors = null;
        this.producers = null;
    }

    public List<ConsumerData> getConsumers() {
        return consumers;
    }

    public List<DistributorData> getDistributors() {
        return distributors;
    }

    public List<ProducerData> getProducers() {
        return producers;
    }

    @Override
    public String toString() {
        return "InitialData{" +
                "consumers=" + consumers +
                ", distributors=" + distributors +
                ", producers=" + producers +
                '}' + "\n";
    }
}
