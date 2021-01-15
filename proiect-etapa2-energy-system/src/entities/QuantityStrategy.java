package entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuantityStrategy implements Strategy{
    private List<Producer> producers;
    private final int energyNeededKW;

    public QuantityStrategy(final List<Producer> producers, final int energyNeededKW) {
        this.producers = new ArrayList<>();
        this.producers.addAll(producers);
        this.energyNeededKW = energyNeededKW;
    }
    @Override
    public List<Producer> apply() {

        Comparator<Producer> comparator = Comparator
                .comparing((Producer::getEnergyPerDistributor), Comparator.reverseOrder())
                .thenComparing(Producer::getId);

        producers = producers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

//        System.out.println("----------------------------------- QUANTITY --------------------------------------");
//        for(Producer p : producers) {
//            System.out.println("type " + p.getEnergyType().getLabel() + " price " + p.getPriceKW()
//                    + " energy " + p.getEnergyPerDistributor() + " id " + p.getId());
//        }

        List<Producer> selectedProducers = new ArrayList<>();

        int sum = 0;
        for(Producer p : producers) {
            if(sum < energyNeededKW) {
                if (p.getCurrentDistributors() < p.getMaxDistributors()) {
                    sum += p.getEnergyPerDistributor();
                    p.addDistributor();
                    selectedProducers.add(p);
                }
            }
        }
//        System.out.println("Energy Needed " + energyNeededKW);
//
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
