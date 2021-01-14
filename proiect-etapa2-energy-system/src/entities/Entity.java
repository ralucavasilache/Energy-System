package entities;
/**
 * Clasa cu metodele si campurile comune celor doua entitati:
 * Consumator si Distribuitor
 */
public abstract class Entity {
    /**
     Id-ul entitatii
     */
    private int id;
    /**
     Bugetul entitatii
     */
    private int budget;
    /**
     Pretul contractului
     */
    private int contractPrice;
    /**
     Daca a dat faliment campul isBankrupt = true
     */
    private boolean isBankrupt = false;

    public Entity(final int id, final int budget) {

        this.id = id;
        this.budget = budget;
    }
    /**
     * Updateaza bugetul
     */
    public abstract void updateBudget();

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getBudget() {
        return budget;
    }

    public final void setBudget(final int budget) {
        this.budget = budget;
    }

    public final boolean isBankrupt() {
        return isBankrupt;
    }

    public final void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public final void setContractPrice(final int contractPrice) {
        this.contractPrice = contractPrice;
    }

    public final int getContractPrice() {
        return contractPrice;
    }
}
