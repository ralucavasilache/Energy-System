package simulation;

import entities.*;
import input.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/**
 * Clasa care simuleaza mecanismul jocului
 */
public final class GameSimulator {
    /**
     * Luna/runda curenta
     * Este -1 daca este prima luna
     */
    private int currentMonth = -1;
    /**
     * Numarul de runde
     */
    private final int numberOfTurns;
    /**
     * Lista cu consumatorii din joc
     */
    private final List<Consumer> consumers;
    /**
     * Lista cu distribuitorii din joc
     */
    private final List<Distributor> distributors;
    /**
     * Lista cu producatorii din joc
     */
    private final List<Producer> producers;
    /**
     * Lista cu update-urile lunare
     */
    private final List<MonthlyUpdate> monthlyUpdates;

    public GameSimulator(final int numberOfTurns,
                         final List<Consumer> consumers,
                         final List<Distributor> distributors,
                         final List<Producer> producers,
                         final List<MonthlyUpdate> monthlyUpdates) {

        this.numberOfTurns = numberOfTurns;
        this.consumers = consumers;
        this.distributors = distributors;
        this.producers = producers;
        this.monthlyUpdates = monthlyUpdates;
    }
    /**
     * Executa pasii simularii pentru fiecare
     * dintre cele numberOfTurns runde
     */
    public void simulateGame() {
        for (int i = 0; i <= numberOfTurns; i++) {
            simulateOneTurn();
        }
    }
    /**
     * Pasii simularii pentru o singura runda
     */
    private void simulateOneTurn() {
        if(currentMonth == -1) {
            simulateFirstTurn();
            return;
        }
        // se citesc update-urile
        update();
        // distributorii calculeaza costul de productie
        setProductionCosts();
        // se stabilesc preturile contractelor
        setContractsPrices();
        // se elimina contractele expirate
        removeContracts();
        // consumatorii aleg ditribuitori
        chooseDistributor();
        // consumatorii primesc salariu si fac platile
        // distribuitorii fac incasarile (inclus in updateBudget din Consumer)
        updateConsumerBudget();
        // distribuitorii isi platesc cheltuielile
        updateDistributorBudget();
        // se actualizează valorile citite din test pentru
        // luna respectivă pentru producători
        applyProducerChanges();
        // se scade numarul de luni ramase din contracte
        updateContractMonth();
        // se alimina din jos consumatorii si distribuitorii care au dat faliment
        removeConsumers();



        removeDistributors();
        // se trece la urmatoarea luna

        changeProducer();

        makeMonthlyStats();


        System.out.println("MONTH         :   " + (currentMonth + 1));
//////        for(Distributor d : distributors) {
//////            System.out.println("distrib id : " + d.getId() + "   producers : " + d.getProducers());
//////        }
//        for(Producer p : producers) {
//            System.out.println("producer id : " + p.getId() + " update : " + p.getMonthlyStats());
//        }
        prinConsumertInfo();
//        prinDistributorInfo();
        //prinProducerInfo();

        currentMonth++;
    }
    private void simulateFirstTurn() {

        // distribuitorii isi aleg producatorii
        chooseProducers();
        // distributorii calculeaza costul de productie
        setProductionCosts();
        // se stabilesc preturile contractelor
        setContractsPrices();
        // consumatorii aleg ditribuitori
        chooseDistributor();
        // consumatorii primesc salariu si fac platile
        // distribuitorii fac incasarile (inclus in updateBudget din Consumer)
        updateConsumerBudget();
        // distribuitorii isi platesc cheltuielile
        updateDistributorBudget();
        // se scade numarul de luni ramase din contracte
        updateContractMonth();
        // se elimina din jos consumatorii si distribuitorii care au dat faliment
        removeConsumers();
        removeDistributors();
        // statisticile lunare
        //makeMonthlyStats();
        // se trece la urmatoarea luna

        prinConsumertInfo();
//        prinDistributorInfo();
        //prinProducerInfo();

        currentMonth++;
    }
    private void prinConsumertInfo() {
        System.out.println("------------------------------------- CONSUMERS ---------------------------------------");
        for(Consumer c : consumers) {
            if(c.getOldDistributor() != null)
            System.out.println("RUNDA : " + currentMonth + " \nid, " + c.getId()
                    + ", budget " + c.getBudget() + ", contractPrice "
                    + c.getContractPrice() + ", isBankrupt " + c.isBankrupt() + ", debt "
                    + c.getDebt() + ", oldDistributor " + c.getOldDistributor().getId()
                    + ", currentDistributor " + c.getCurrentDistributor().getId());
        }
        System.out.println();
    }
    private void prinDistributorInfo() {
        System.out.println("------------------------------------- DISTRIBUTORS ---------------------------------------");
        for(Distributor c : distributors) {
            System.out.println("RUNDA : " + currentMonth + " \nid, " + c.getId() + ", energyNeededKW "
                    + c.getEnergyNeededKW() + ", contractPrice " + c.getContractPrice()
                    + ", budget " + c.getBudget()  + ", producerStrategy " + c.getProducerStrategy() +
                    ", isBankrupt " + c.isBankrupt() + ", infrastructureCost " + c.getInfrastructureCost()
                    + ", productionCost " + c.getProductionCost() +
                    ", contracts : \n"  +  c.getContracts());
        }
        System.out.println();
    }
    private void prinProducerInfo() {
        System.out.println("------------------------------------- PRODUCERS ---------------------------------------");
        for(Producer c : producers) {
            System.out.println("RUNDA " + currentMonth + " \nid " + c.getId() + " monthlyStats "
                            + c.getMonthlyStats());
        }
        System.out.println();
    }
    // completeaza statisticile lunare
    private void makeMonthlyStats() {

        for(Producer p : producers) {
            p.addMonthlyState(currentMonth);
        }
//        System.out.println("MONTHLYSTATS");
        for(Distributor d : distributors) {
            for(Producer p : d.getProducers()) {
//                System.out.println("###### id distr " + d.getId() + "  id prod " + p.getId() + " month " + (currentMonth+1));
                p.addId(d.getId(), currentMonth );
            }
        }
    }
    /**
     * Gaseste pozitia in lista de distribuitori
     * a furnizorului cu cel mai mic pret, care sa nu fie falimentat
     */
    private int bestDistributor() {
        int minPrice = Integer.MAX_VALUE;
        int distributorId = -1;
        for (int i = 0; i < distributors.size(); i++) {
            if (!distributors.get(i).isBankrupt()) {
                if(distributors.get(i).getContractPrice() < minPrice) {
                    distributorId = distributors.get(i).getId();
                    minPrice = distributors.get(i).getContractPrice();
                }
            }
        }
        if(minPrice == Integer.MAX_VALUE) {
            System.out.println("Toti distribuitorii au dat faliment!");
            return  0;
        } else {
            return distributorId;
        }

    }

    /**
     * Consumatorii isi aleg distribuitor
     */
    private void chooseDistributor() {
        int i = bestDistributor();

        for (Consumer c : consumers) {
            // daca consumatorul nu are distribuitor si nici nu e falimentat
            if (!c.getHasDistributor() && (!c.isBankrupt())) {
                // se creeaza un contract nou si se adauga in lista de
                // contracte ale distribuitorului
                Contract newContract = new Contract(c.getId(),
                                                    distributors.get(i).getContractPrice(),
                                                    distributors.get(i).getContractLength());
                distributors.get(i).getContracts().add(newContract);
                // consumatorul are distribuitor
                c.setHasDistributor(true);
                c.setContractPrice(distributors.get(i).getContractPrice());
                c.setCurrentDistributor(distributors.get(i));
            }
        }
    }
    public void chooseProducers() {
        for(Distributor d : distributors) {
            switch (d.getProducerStrategy()) {
                case "GREEN" -> d.applyStrategy(new GreenStrategy(producers, d.getEnergyNeededKW()));
                case "PRICE" -> d.applyStrategy(new PriceStrategy(producers, d.getEnergyNeededKW()));
                case "QUANTITY" -> d.applyStrategy(new QuantityStrategy(producers, d.getEnergyNeededKW()));
            }
        }
    }
    /**
     * Se updateaza bugetele consumatorilor
     */
    private void updateConsumerBudget() {
        for (Consumer c : consumers) {
            if (!c.isBankrupt()) {
                c.updateBudget();
            }
        }
    }
    /**
     * Se updateaza bugetele distribuitorilor
     */
    private void updateDistributorBudget() {
        for (Distributor d : distributors) {
            if (!d.isBankrupt()) {
                d.updateBudget();
            }
        }
    }
    /**
     * Se calculeaza si se seteaza preturile contractelor
     * pentru toti distribuitorii
     */
    private void setContractsPrices() {
        for (Distributor d : distributors) {
            d.setContractPrice();
        }
    }
    /**
     * Se fac update-urile
     */
    private void update() {
        // daca nu este prima runda a jocului
        if (currentMonth != -1) {
            // se actualizeaza valorile pentru distribuitori
            applyDistributorChanges();
            // se adauga consumatori noi
            addNewConsumer();
        }
    }
    /**
     * Se adauga consumatori noi (cititi din monthlyUpdates)
     */
    private void addNewConsumer() {
        for (ConsumerData c : monthlyUpdates.get(currentMonth).getNewConsumers()) {
            Consumer newConsumer = new Consumer(c.getId(), c.getInitialBudget(),
                                                c.getMonthlyIncome());
            consumers.add(newConsumer);
        }
    }
    /**
     * Se adauga consumatori noi (cititi din monthlyUpdates)
     */
    private void applyDistributorChanges() {
        for(DistributorChanges d : monthlyUpdates.get(currentMonth).getDistributorChanges()) {
            Distributor distributor = getDistributor(d.getId());
            distributor.setInfrastructureCost(d.getInfrastructureCost());
        }
    }
    private void applyProducerChanges() {
        if(currentMonth != -1) {
            for (ProducerChanges p : monthlyUpdates.get(currentMonth).getProducerChanges()) {
                Producer producer = getProducer(p.getId());
                producer.setEnergyPerDistributor(p.getEnergyPerDistributor());
            }
        }
    }
    /**
     * Se returneaza distribuitorul care id-ul dat ca parametru
     */
    private Distributor getDistributor(final int id) {
        for (Distributor d : distributors) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }
    /**
     * Se returneaza consumatorul care id-ul dat ca parametru
     */
    private Consumer getConsumer(final int id) {
        for (Consumer c : consumers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    /**
     * Se returneaza producatorul care id-ul dat ca parametru
     */
    private Producer getProducer(final int id) {
        for (Producer p : producers) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    /**
     * Se scad lunile ramase din toate contractele
     */
    private void updateContractMonth() {
        for (Distributor d : distributors) {
            for (Contract contract : d.getContracts()) {
                contract.decrementRemainedContractMonths();
            }
        }
    }
    /**
     * Se elimina contractele care au expirat
     */
    private void removeContracts() {
        for (Distributor d : distributors) {
            Iterator<Contract> i = d.getContracts().iterator();

            while (i.hasNext()) {
                Contract contract = i.next();
                // daca remainedContractMonths == 0,
                // contractul a expirat
                if (contract.getRemainedContractMonths() == 0) {
                    Consumer c = getConsumer(contract.getConsumerId());

                    // consumatorul nu mai are distribuitor
                    assert c != null;
                    c.resetCurrentDistributor();
                    // se alimina contractul
                    i.remove();
                }
            }
        }
    }
    /**
     * Se elimina toate contractele incheiate cu consumatori falimentati
     */
    private void removeConsumers() {
        for (Consumer c : consumers) {
            if (c.isBankrupt()) {
                for (Distributor d : distributors) {
                    d.getContracts().removeIf(contract -> contract.getConsumerId() == c.getId());
                }
            }
        }
    }
    /**
     * Se elimina din joc toti distribuitorii care au dat faliment
     */
    private void removeDistributors() {
        for (Distributor d : distributors) {
            if (d.isBankrupt()) {
                for (Contract contract : d.getContracts()) {
                    Consumer c = getConsumer(contract.getConsumerId());
                    assert c != null;
                    // consumatorul care avea contract cu un distribuitor
                    // care a dat faliment nu va mai avea distribuitor
                    c.resetCurrentDistributor();
                }
                for(Producer p : d.getProducers()) {
                    p.deleteObserver(d);
                }
            }
        }
    }
    private void changeProducer() {
        for(Distributor d : distributors) {
            if(!d.isBankrupt() && d.isUpdateNeeded()) {
                for(Producer p : d.getProducers()){

                    p.deleteObserver(d);
                    p.decCurrentDistrb();
                }

                switch (d.getProducerStrategy()) {
                    case "GREEN" -> d.applyStrategy(new GreenStrategy(producers, d.getEnergyNeededKW()));
                    case "PRICE" -> d.applyStrategy(new PriceStrategy(producers, d.getEnergyNeededKW()));
                    case "QUANTITY" -> d.applyStrategy(new QuantityStrategy(producers, d.getEnergyNeededKW()));
                }
                d.setUpdateNeeded(false);
            }

        }
    }
    public List<Consumer> getConsumers() {
        return consumers;
    }

    public List<Distributor> getDistributors() {
        return distributors;
    }
    private void setProductionCosts() {
        for(Distributor d : distributors) {
            d.calculateProductionCost();
        }
    }

    public List<Producer> getProducers() {
        return producers;
    }
}
