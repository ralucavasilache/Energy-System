package input;

import java.util.List;
/**
 * Clasa care datele parsate din format JSON
 */
public final class InputData {

    private final int numberOfTurns;
    private final InitialData initialData;
    private final List<MonthlyUpdate> monthlyUpdates;

    public InputData() {

        numberOfTurns = -1;
        initialData = null;
        monthlyUpdates = null;

    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public List<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "numberOfTurns=" + numberOfTurns +
                ", initialData=" + initialData +
                ", monthlyUpdates=" + monthlyUpdates +
                '}' + "\n";
    }
}
