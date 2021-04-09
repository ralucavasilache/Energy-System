package strategies;

import entities.Producer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 Clasa care contine implementarile specifice pentru strategia PRICE
 */
public final class PriceStrategy implements Strategy {

    private  List<Producer> producers;
    private final int energyNeededKW;

    public PriceStrategy(final List<Producer> producers, final int energyNeededKW) {

        this.producers = new ArrayList<>();
        this.producers.addAll(producers);
        this.energyNeededKW = energyNeededKW;
    }

    @Override
    public List<Producer> apply() {

        // producatorii vor fi sortati dupa pret, dupa cantitatea de energie(descrescator)
        // si dupa id
        Comparator<Producer> comparator = Comparator
                .comparing(Producer::getPriceKW)
                .thenComparing((Producer::getEnergyPerDistributor), Comparator.reverseOrder())
                .thenComparing(Producer::getId);

        producers = producers.stream()
                             .sorted(comparator)
                             .collect(Collectors.toList());

        // se selecteaza din lista sortata cati producatori este nevoie pentru
        // asigurarea cantitatii de energie necesare
        return Strategy.selectProducers(producers, energyNeededKW);
    }
}
