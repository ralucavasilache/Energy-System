package output;
/**
 * Clasa care contine datele despre un consumator
 * care vor fi scrise in fisierul de iesire in format JSON
 */
public final class ConsumerOutput {

    private final int id;
    private final boolean isBankrupt;
    private final int budget;

    public ConsumerOutput(final int id, final boolean isBankrupt, final int budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public int getBudget() {
        return budget;
    }
}
