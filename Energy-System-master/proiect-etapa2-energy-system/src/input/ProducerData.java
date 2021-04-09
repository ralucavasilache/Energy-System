package input;
/**
 * Clasa care contine datele despre un producator,
 * parsate din format JSON
 */
public final class ProducerData {
    private final int id;
    private final String energyType;
    private final int maxDistributors;
    private final double priceKW;
    private final int energyPerDistributor;

    public ProducerData() {
        this.id = -1;
        this.energyType = null;
        this.maxDistributors = -1;
        this.priceKW = -1;
        this.energyPerDistributor = -1;
    }

    public int getId() {
        return id;
    }

    public String getEnergyType() {
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

}
