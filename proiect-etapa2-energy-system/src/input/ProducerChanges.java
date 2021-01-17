package input;
/**
 * Clasa care contine schimbarile pentru un producator,
 * parsate din format JSON
 */
public final class ProducerChanges {

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

}
