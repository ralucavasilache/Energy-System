package strategies;

import entities.Producer;

import java.util.ArrayList;
import java.util.List;
/**
 Interfata comuna pentru cele 3 tipuri de strategii
 */
public interface Strategy {
    /**
     * Aplica strategia
     * @return lista cu producatorii selectati
     */
    List<Producer> apply();
    /**
     * Selecteaza producatorii dintr-o lista sortata
     * care pot oferi o cantitate de energie egala cu energyNeededKW
     * @return lista cu producatorii selectati
     */
    static List<Producer> selectProducers(List<Producer> producers, int energyNeededKW) {

        List<Producer> selectedProducers = new ArrayList<>();
        int sum = 0;

        for (Producer p : producers) {
            if (sum < energyNeededKW) {
                if (p.getCurrentDistributors() < p.getMaxDistributors()) {
                    sum += p.getEnergyPerDistributor();
                    // creste numarul de distribuitori curenti ai producatorului
                    p.addDistributor();
                    selectedProducers.add(p);
                }
            }
        }
        return selectedProducers;
    }
}
