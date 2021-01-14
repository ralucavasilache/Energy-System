package input;
/**
 * Clasa care contine datele despre un schimbarile de costuri,
 * parsate din format JSON
 */
public final class CostChangeData {

    private final int id;
    private final int infrastructureCost;
    private final int productionCost;

    public CostChangeData() {

        this.id = -1;
        this.infrastructureCost = -1;
        this.productionCost = -1;
    }

    public int getId() {
        return id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public int getProductionCost() {
        return productionCost;
    }

    @Override
    public String toString() {
        return "CostChangeData{" +
                "id=" + id +
                ", infrastructureCost=" + infrastructureCost +
                ", productionCost=" + productionCost +
                '}' + "\n";
    }
}
