package input;

public class DistributorChanges {
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

    @Override
    public String toString() {
        return "DistributorChanges{" +
                "id=" + id +
                ", infrastructureCost=" + infrastructureCost +
                '}' + "\n";
    }
}
