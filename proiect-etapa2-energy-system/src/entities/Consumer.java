package entities;

import utils.Constants;

/**
 Clasa contine campurile si metodele specifice unui consumator
 */
public final class Consumer extends Entity {
    /**
     Venitul lunar
     */
    private int monthlyIncome;
    /**
     Valoarea restantei
     */
    private int debt;
    /**
     Daca consumatorul are un furnizor, hasDistributor = true
     */
    private boolean hasDistributor = false;
    /**
     Distribuitorul curent
     */
    private Distributor currentDistributor;
    /**
     Distribuitorul catre care are restanta
     */
    private Distributor oldDistributor;

    public Consumer(final int id, final int budget, final int monthlyIncome) {

        super(id, budget);
        this.monthlyIncome = monthlyIncome;
    }
    /**
     * Calculeaza costul total al platilor pe care le face consumatorul intr-o luna
     * @return un int, reprezentand cheltuielile lunare
     */
    public int calculatePayments() {
        return  (int) (Math.round(Math.floor(Constants.DEBT_CONST * debt))
                       + super.getContractPrice());
    }
    /**
     * Updateaza bugetul: primeste salariul si plateste cheltuieli
     */
    public void updateBudget() {

        int finalBudget = super.getBudget() + monthlyIncome - calculatePayments();

        // daca finalBudget < 0 si debt == 0, consumatorul nu
        // a mai avut nicio restanta deci poate ramane restant luna aceasta
        if (finalBudget < 0 && debt == 0) {
            // primeste salariu
            super.setBudget(super.getBudget() + monthlyIncome);
            // retinem datoria
            debt = (int) Math.round(Math.floor(Constants.DEBT_CONST * super.getContractPrice()));
            // retinem distribuitorul care care este restant
            oldDistributor = currentDistributor;

        // consumatorul nu-si poate plati cheltuielile (are deja restanta de luna anterioara)
        // si este declarat falimentat
        } else if (finalBudget < 0 && debt > 0) {
            super.setBudget(super.getBudget() + monthlyIncome);
            super.setBankrupt(true);

        // consumatorul isi poate plati cheltuielile
        } else {
            super.setBudget(finalBudget);
            // distribuitorul colecteaza taxele
            currentDistributor.collectMoney(super.getContractPrice());

            // daca are restanta, o plateste
            if (oldDistributor != null) {
                oldDistributor.collectMoney(debt);
                // se reseteaza restanta si distribuitorul caruia ii era restant
                resetOldDistributor();
            }
        }
    }
    /**
     * Reseteaza datele despre distribuitorul curent
     */
    public void resetCurrentDistributor() {
        hasDistributor = false;
        currentDistributor = null;
        super.setContractPrice(0);
    }
    /**
     * Reseteaza datele despre distribuitorul catre care are restanta
     */
    public void resetOldDistributor() {
        oldDistributor = null;
        debt = 0;
    }

    public boolean getHasDistributor() {
        return hasDistributor;
    }

    public void setHasDistributor(final boolean hasDistributor) {
        this.hasDistributor = hasDistributor;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setCurrentDistributor(final Distributor currentDistributor) {
        this.currentDistributor = currentDistributor;
    }

}
