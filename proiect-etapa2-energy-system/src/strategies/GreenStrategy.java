package strategies;

import entities.Producer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 Clasa care contine implementarile specifice pentru strategia GREEN
 */
public final class GreenStrategy implements Strategy {
    /**
     Toti producatorii din baza de date
     */
    private  List<Producer> producers;
    /**
     Cantitatea de energie necesara
     */
    private final int energyNeededKW;

    public GreenStrategy(final List<Producer> producers, final int energyNeededKW) {

        this.producers = new ArrayList<>();
        this.producers.addAll(producers);
        this.energyNeededKW = energyNeededKW;
    }

    @Override
    public List<Producer> apply() {

        // lista cu toti producatorii sortati
        List<Producer> sortedProducers = new ArrayList<>();

        // ii adaugam la inceputul listei doar pe cei cu energie verde
        for (Producer producer : producers) {
            if (producer.getEnergyType().isRenewable()) {
                sortedProducers.add(producer);
            }
        }

        // in producers vor ramane doar producatorii care nu au energie verde
        producers.removeIf(producer -> producer.getEnergyType().isRenewable());

        // producatorii vor fi sortati dupa pret, dupa cantitatea de energie(descrescator)
        // si dupa id
        Comparator<Producer> comparator = Comparator
                .comparing(Producer::getPriceKW)
                .thenComparing((Producer::getEnergyPerDistributor), Comparator.reverseOrder())
                .thenComparing(Producer::getId);

        // sortam lista cu producatorii fara energie verde
        producers = producers.stream()
                             .sorted(comparator)
                             .collect(Collectors.toList());

        // sortam lista cu producatorii cu energie verde
        sortedProducers = sortedProducers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        // la finalul listei sortate cu producatori verzi se adauga lista sortata
        // cu producatori care nu dispun de energie regenerabila
        sortedProducers.addAll(producers);

        // se selecteaza din lista sortata cati producatori este nevoie pentru
        // asigurarea cantitatii de energie necesare
        return Strategy.selectProducers(sortedProducers, energyNeededKW);
    }
}
