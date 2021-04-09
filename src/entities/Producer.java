package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
/**
 Clasa care contine campurile si implementarile specifice unui producator
 */
public final class Producer extends Observable {

    private int id;
    private EnergyType energyType;
    private final int maxDistributors;
    private int currentDistributors = 0;
    private final double priceKW;
    private  int energyPerDistributor;
    private List<MonthlyStats> monthlyStats;

    public Producer(final int id, final String energyType, final int maxDistributors,
                    final double priceKW, final int energyPerDistributor) {

        this.id = id;
        createEnergyType(energyType);
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        monthlyStats = new ArrayList<>();
    }
    /**
     * Creeaza un enum
     * @param type, tipul de energie
     */
    private void createEnergyType(String type) {

        if (type.compareTo("WIND") == 0) {
            this.energyType = EnergyType.WIND;

        } else if (type.compareTo("SOLAR") == 0) {
            this.energyType = EnergyType.SOLAR;

        } else if (type.compareTo("HYDRO") == 0) {
            this.energyType = EnergyType.HYDRO;

        } else if (type.compareTo("COAL") == 0) {
            this.energyType = EnergyType.COAL;

        } else if (type.compareTo("NUCLEAR") == 0) {
            this.energyType = EnergyType.NUCLEAR;
        }
    }
    /**
     * Cand producatorului i se modifica cantitatea de energie pe care
     * o poate oferi, acesta notifica toti distribuitorii abonati la el
     * @param energyPerDistributor, noua cantitate de energie
     */
    public void setEnergyPerDistributor(final int energyPerDistributor) {

        this.energyPerDistributor = energyPerDistributor;
        setChanged();
        notifyObservers();
    }
    /**
     * Creeaza si adauga o noua statistica pentru o luna
     * @param month, luna corespunzatoare
     */
    public void addMonthlyState(final int month) {

        MonthlyStats newMonth = new MonthlyStats(month + 1);
        monthlyStats.add(newMonth);
    }
    /**
     * Adauga id-ul unui distribuitor in statistici
     * @param distributorId, id-ul distribuitorului
     * @param month, luna in care distribuitorul este abonat
     */
    public void addId(final int distributorId, final int month) {

        monthlyStats.get(month).getDistributorsIds().add(distributorId);
    }
    /**
     * Scade numarul de distribuitori abonati
     */
    public void decrementCurrentDistributors() {
        currentDistributors--;
    }
    /**
     * Creste numarul de distribuitori abonati
     */
    public void addDistributor() {
        this.currentDistributors++;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public int getCurrentDistributors() {
        return currentDistributors;
    }

    public void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
    }

    public List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(final List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
