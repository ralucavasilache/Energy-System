package entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GreenStrategy implements Strategy{
    private  List<Producer> producers;
    private final int energyNeededKW;

    public GreenStrategy(final List<Producer> producers, final int energyNeededKW) {
        this.producers = new ArrayList<>();
        this.producers.addAll(producers);
        this.energyNeededKW = energyNeededKW;
    }

    @Override
    public List<Producer> apply() {
        List<Producer> sortedProducers = new ArrayList<>();

        for(Producer producer : producers) {
            if(producer.getEnergyType().isRenewable()) {
                sortedProducers.add(producer);
            }
        }

        producers.removeIf(producer -> producer.getEnergyType().isRenewable());

        Comparator<Producer> comparator = Comparator
                .comparing(Producer::getPriceKW)
                .thenComparing((Producer::getEnergyPerDistributor), Comparator.reverseOrder())
                .thenComparing(Producer::getId);

        producers = producers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        sortedProducers = sortedProducers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        sortedProducers.addAll(producers);
//        System.out.println("----------------------------------- GREEN --------------------------------------");
//
//        for(Producer p : sortedProducers) {
//            System.out.println("type " + p.getEnergyType().getLabel() + " price " + p.getPriceKW()
//                    + " energy " + p.getEnergyPerDistributor() + " id " + p.getId());
//        }

        List<Producer> selectedProducers = new ArrayList<>();

//        System.out.println();
//        System.out.println("Energy Needed " + energyNeededKW);

        int sum = 0;
        for(Producer p : sortedProducers) {
            if(sum < energyNeededKW) {
                if (p.getCurrentDistributors() < p.getMaxDistributors()) {
                    sum += p.getEnergyPerDistributor();
                    p.addDistributor();
                    selectedProducers.add(p);
                }
            }
        }

//        for(Producer p : selectedProducers) {
//            System.out.println("type " + p.getEnergyType().getLabel() + " price " + p.getPriceKW()
//                    + " energy " + p.getEnergyPerDistributor() + " id " + p.getId());
//        }
//
//        System.out.println("Energy taken " + sum);
//        System.out.println();

        return selectedProducers;
    }
}
