package input;
/**
 * Clasa care contine schimbarile pentru un distribuitor,
 * parsate din format JSON
 */
public final class DistributorChanges {

    private final int id;
    private final int infrastructureCost;

    public DistributorChanges() {
        this.id = -1;
        this.infrastructureCost = -1;
    }

    public int getId() {
        return id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

}
