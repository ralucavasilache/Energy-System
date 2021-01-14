package input;

public class ProducerChanges {
    private final int id;
    private final int energyPerDistributor;

    public ProducerChanges() {
        this.id = -1;
        this.energyPerDistributor = -1;
    }

    public int getId() {
        return id;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    @Override
    public String toString() {
        return "ProducerChanges{" +
                "id=" + id +
                ", energyPerDistributor=" + energyPerDistributor +
                '}' + "\n";
    }
}
