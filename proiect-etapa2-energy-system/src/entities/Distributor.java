package entities;

import utils.Constants;

import java.util.ArrayList;
import java.util.List;
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
    private int productionCost;
    /**
     Lista de contracte incheiate
     */
    private final List<Contract> contracts = new ArrayList<>();

    public Distributor(final int id, final int contractLength,
                       final int budget, final int infrastructureCost,
                       final int productionCost) {

        super(id, budget);
        this.contractLength = contractLength;
        this.infrastructureCost = infrastructureCost;
        this.productionCost = productionCost;
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

    public void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }

}
