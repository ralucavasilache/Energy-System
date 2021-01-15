package entities;

import utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

/**
 Clasa contine campurile si metodele specifice unui distributor
 */
public final class Distributor extends Entity {
    /**
     Durata contractului pe care il pune la dispozitie
     */
    private final int contractLength;
    /**
     Costul de infrastructura platit lunar
     */
    private int infrastructureCost;
    /**
     Costul de productie platit lunar
     */
    private final int energyNeededKW;

    private final String producerStrategy;

    private int productionCost;
    /**
     Lista de contracte incheiate
     */
    private final List<Contract> contracts = new ArrayList<>();
    private List<Producer> producers;

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
//        System.out.println("COST" + productionCost);
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

    public int getContractLength() {
        return contractLength;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public void calculateProductionCost() {
        double cost = 0;
//        System.out.println("###### calculateProductionCost" + producers.size());

        for(Producer p : producers) {
//            System.out.println("energy " + p.getEnergyPerDistributor() + " priceKw " + p.getPriceKW());
            cost += p.getEnergyPerDistributor() * p.getPriceKW();
        }
        int productionCost =  (int) Math.round(Math.floor(cost / 10));
        this.productionCost = productionCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void applyStrategy(Strategy strategy) {
        producers = strategy.apply();
    }

    public void setProductionCost(int productionCost) {
        this.productionCost = productionCost;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }
}
