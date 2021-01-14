package input;
/**
 * Clasa care contine datele despre un consumator,
 * parsate din format JSON
 */
public final class ConsumerData {

    private final int id;
    private final int initialBudget;
    private final int monthlyIncome;

    public ConsumerData() {

        this.id = -1;
        this.initialBudget = -1;
        this.monthlyIncome = -1;
    }

    public int getId() {
        return id;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    @Override
    public String toString() {
        return "ConsumerData{" +
                "id=" + id +
                ", initialBudget=" + initialBudget +
                ", monthlyIncome=" + monthlyIncome +
                '}' + "\n";
    }
}
