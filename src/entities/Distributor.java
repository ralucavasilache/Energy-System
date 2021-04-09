package entities;

import strategies.Strategy;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 Clasa contine campurile si metodele specifice unui distributor
 */
public final class Distributor extends Entity implements Observer {

    private final int contractLength;
    private int infrastructureCost;
    private final int energyNeededKW;
    private final String producerStrategy;
    private int productionCost;
    private final List<Contract> contracts = new ArrayList<>();
    private List<Producer> producers;
    private boolean updateNeeded = false;


    public Distributor(final int id, final int contractLength,
                       final int budget, final int infrastructureCost,
                       final int energyNeededKW, final  String producerStrategy) {

        super(id, budget);
        this.contractLength = contractLength;
        this.infrastructureCost = infrastructureCost;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategy;

    }
    /**
     * Calculeaza profitul
     * @return un int reprezentand profitul
     */
    public int getProfit() {
        return (int) Math.round(Math.floor(Constants.PROFIT_CONST * productionCost));
    }
    /**
     * Updateaza bugetul: plateste datoriile lunare
     */
    public void updateBudget() {
        int finalBudget = super.getBudget() - getMonthlyExpenses();
        // Daca finalBudget < 0, nu-si poate plati datroriile
        // si este declarat falimentul
        if (finalBudget < 0) {
            super.setBudget(finalBudget);
            super.setBankrupt(true);
        } else {
            super.setBudget(finalBudget);
        }
    }
    /**
     * Calculeaza si seteaza pretul contractului
     */
    public void setContractPrice() {
        int contractPrice;
        // daca are consumatori
        if (contracts.size() != 0) {
            contractPrice = (int) Math.round(Math.floor(infrastructureCost * 1.0 / contracts.size())
                                             + productionCost + getProfit());
        // daca nu are consumatori
        } else {
            contractPrice = infrastructureCost + productionCost + getProfit();
        }
        super.setContractPrice(contractPrice);
    }
    /**
     * Incaseaza platile de la consumatori
     * @param money, suma platita de consumator
     */
    public void collectMoney(final int money) {
        super.setBudget(super.getBudget() + money);
    }
    /**
     * Calculeaza valoarea cheltuielilor lunare
     * @return int, cheltuielile lunare
     */
    public int getMonthlyExpenses() {
        return infrastructureCost + productionCost * contracts.size();
    }
    /**
     * Aplica strategia si seteaza lista de producatori;
     * Se adauga distribuitorul curent in lista de observers
     * pentru fiecare producator ales
     * @param strategy, strategia aplicata
     */
    public void applyStrategy(Strategy strategy) {
        producers = strategy.apply();

        for (Producer p : producers) {
            p.addObserver(this);
        }
    }
    /**
     * Calculeaza si seteaza costul de productie
     */
    public void calculateProductionCost() {
        double cost = 0;

        for (Producer p : producers) {
            cost += p.getEnergyPerDistributor() * p.getPriceKW();
        }
        productionCost = (int) Math.round(Math.floor(cost / Constants.COST_CONST));
    }

    public int getContractLength() {
        return contractLength;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public boolean isUpdateNeeded() {
        return updateNeeded;
    }

    public void setUpdateNeeded(boolean updateNeeded) {
        this.updateNeeded = updateNeeded;
    }

    @Override
    public void update(Observable o, Object arg) {
        updateNeeded = true;
    }

}
