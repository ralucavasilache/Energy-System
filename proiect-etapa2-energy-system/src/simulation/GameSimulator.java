package simulation;

import entities.Consumer;
import entities.Contract;
import entities.Distributor;
import input.ConsumerData;
import input.CostChangeData;
import input.MonthlyUpdate;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/**
 * Clasa care simuleaza mecanismul jocului
 */
public final class GameSimulator {
//    /**
//     * Luna/runda curenta
//     * Este -1 daca este prima luna
//     */
//    private int currentMonth = -1;
//    /**
//     * Numarul de runde
//     */
//    private final int numberOfTurns;
//    /**
//     * Lista cu consumatorii din joc
//     */
//    private final List<Consumer> consumers;
//    /**
//     * Lista cu distribuitorii din joc
//     */
//    private final List<Distributor> distributors;
//    /**
//     * Lista cu update-urile lunare
//     */
//    private final List<MonthlyUpdate> monthlyUpdates;
//
//    public GameSimulator(final int numberOfTurns,
//                         final List<Consumer> consumers,
//                         final List<Distributor> distributors,
//                         final List<MonthlyUpdate> monthlyUpdates) {
//
//        this.numberOfTurns = numberOfTurns;
//        this.consumers = consumers;
//        this.distributors = distributors;
//        this.monthlyUpdates = monthlyUpdates;
//    }
//    /**
//     * Executa pasii simularii pentru fiecare
//     * dintre cele numberOfTurns runde
//     */
//    public void simulateGame() {
//        for (int i = 0; i <= numberOfTurns; i++) {
//            simulateOneTurn();
//        }
//    }
//    /**
//     * Pasii simularii pentru o singura runda
//     */
//    private void simulateOneTurn() {
//        // se citesc update-urile
//        update();
//        // se stabilesc preturile contractelor
//        setContractsPrices();
//        // se cauta cea mai buna oferta
//        sortDistributors();
//        // se elimina contractele expirate
//        removeContracts();
//        // consumatorii aleg ditribuitori
//        chooseDistributor();
//        // consumatorii primesc salariu si fac platile
//        // distribuitorii fac incasarile (inclus in updateBudget din Consumer)
//        updateConsumerBudget();
//        // distribuitorii isi platesc cheltuielile
//        updateDistributorBudget();
//        // se scade numarul de luni ramase din contracte
//        updateContractMonth();
//        // se alimina din jos consumatorii si distribuitorii care au dat faliment
//        removeConsumers();
//        removeDistributors();
//        // se trece la urmatoarea luna
//        currentMonth++;
//    }
//    /**
//     * Sorteaza distribuitorii crescator dupa pretul contractului,
//     * apoi dupa id
//     */
//    private void sortDistributors() {
//        Comparator<Distributor> comparator = (d1, d2) -> {
//            if (d1.getContractPrice() != d2.getContractPrice()) {
//                return d1.getContractPrice() - d2.getContractPrice();
//            } else {
//                return d1.getId() - d2.getId();
//            }
//        };
//        distributors.sort(comparator);
//    }
//    /**
//     * Gaseste pozitia in lista de distribuitori
//     * a furnizorului cu cel mai mic pret, care sa nu fie falimentat
//     */
//    private int bestDistributor() {
//        for (int i = 0; i < distributors.size(); i++) {
//            if (!distributors.get(i).isBankrupt()) {
//                return i;
//            }
//        }
//        System.out.println("Toti distribuitorii au dat faliment!");
//        return  0;
//    }
//
//    /**
//     * Consumatorii isi aleg distribuitor
//     */
//    private void chooseDistributor() {
//        int i = bestDistributor();
//
//        for (Consumer c : consumers) {
//            // daca consumatorul nu are distribuitor si nici nu e falimentat
//            if (!c.getHasDistributor() && (!c.isBankrupt())) {
//                // se creeaza un contract nou si se adauga in lista de
//                // contracte ale distribuitorului
//                Contract newContract = new Contract(c.getId(),
//                                                    distributors.get(i).getContractPrice(),
//                                                    distributors.get(i).getContractLength());
//                distributors.get(i).getContracts().add(newContract);
//                // consumatorul are distribuitor
//                c.setHasDistributor(true);
//                c.setContractPrice(distributors.get(i).getContractPrice());
//                c.setCurrentDistributor(distributors.get(i));
//            }
//        }
//    }
//    /**
//     * Se updateaza bugetele consumatorilor
//     */
//    private void updateConsumerBudget() {
//        for (Consumer c : consumers) {
//            if (!c.isBankrupt()) {
//                c.updateBudget();
//            }
//        }
//    }
//    /**
//     * Se updateaza bugetele distribuitorilor
//     */
//    private void updateDistributorBudget() {
//        for (Distributor d : distributors) {
//            if (!d.isBankrupt()) {
//                d.updateBudget();
//            }
//        }
//    }
//    /**
//     * Se calculeaza si se seteaza preturile contractelor
//     * pentru toti distribuitorii
//     */
//    private void setContractsPrices() {
//        for (Distributor d : distributors) {
//            d.setContractPrice();
//        }
//    }
//    /**
//     * Se fac update-urile
//     */
//    private void update() {
//        // daca nu este prima runda a jocului
//        if (currentMonth != -1) {
//            // se adauga consumatori noi
//            addNewConsumer();
//            // se schimba costurile de infrastructura
//            // si productie (daca e cazul)
//            changeCosts();
//        }
//    }
//    /**
//     * Se adauga consumatori noi (cititi din monthlyUpdates)
//     */
//    private void addNewConsumer() {
//        for (ConsumerData c : monthlyUpdates.get(currentMonth).getNewConsumers()) {
//            Consumer newConsumer = new Consumer(c.getId(), c.getInitialBudget(),
//                                                c.getMonthlyIncome());
//            consumers.add(newConsumer);
//        }
//    }
//    /**
//     * Se adauga consumatori noi (cititi din monthlyUpdates)
//     */
//    private void changeCosts() {
//        for (CostChangeData c : monthlyUpdates.get(currentMonth).getCostsChanges()) {
//            Distributor d = getDistributor(c.getId());
//
//            assert d != null;
//            d.setInfrastructureCost(c.getInfrastructureCost());
//            d.setProductionCost(c.getProductionCost());
//        }
//    }
//    /**
//     * Se returneaza distribuitorul care id-ul dat ca parametru
//     */
//    private Distributor getDistributor(final int id) {
//        for (Distributor d : distributors) {
//            if (d.getId() == id) {
//                return d;
//            }
//        }
//        return null;
//    }
//    /**
//     * Se returneaza consumatorul care id-ul dat ca parametru
//     */
//    private Consumer getConsumer(final int id) {
//        for (Consumer c : consumers) {
//            if (c.getId() == id) {
//                return c;
//            }
//        }
//        return null;
//    }
//    /**
//     * Se scad lunile ramase din toate contractele
//     */
//    private void updateContractMonth() {
//        for (Distributor d : distributors) {
//            for (Contract contract : d.getContracts()) {
//                contract.decrementRemainedContractMonths();
//            }
//        }
//    }
//    /**
//     * Se elimina contractele care au expirat
//     */
//    private void removeContracts() {
//        for (Distributor d : distributors) {
//            Iterator<Contract> i = d.getContracts().iterator();
//
//            while (i.hasNext()) {
//                Contract contract = i.next();
//                // daca remainedContractMonths == 0,
//                // contractul a expirat
//                if (contract.getRemainedContractMonths() == 0) {
//                    Consumer c = getConsumer(contract.getConsumerId());
//
//                    // consumatorul nu mai are distribuitor
//                    assert c != null;
//                    c.resetCurrentDistributor();
//                    // se alimina contractul
//                    i.remove();
//                }
//            }
//        }
//    }
//    /**
//     * Se elimina toate contractele incheiate cu consumatori falimentati
//     */
//    private void removeConsumers() {
//        for (Consumer c : consumers) {
//            if (c.isBankrupt()) {
//                for (Distributor d : distributors) {
//                    d.getContracts().removeIf(contract -> contract.getConsumerId() == c.getId());
//                }
//            }
//        }
//    }
//    /**
//     * Se elimina din joc toti distribuitorii care au dat faliment
//     */
//    private void removeDistributors() {
//        for (Distributor d : distributors) {
//            if (d.isBankrupt()) {
//                for (Contract contract : d.getContracts()) {
//                    Consumer c = getConsumer(contract.getConsumerId());
//                    assert c != null;
//                    // consumatorul care avea contract cu un distribuitor
//                    // care a dat faliment nu va mai avea distribuitor
//                    c.resetCurrentDistributor();
//                }
//            }
//        }
//    }
//
//    public List<Consumer> getConsumers() {
//        return consumers;
//    }
//
//    public List<Distributor> getDistributors() {
//        return distributors;
//    }
}
