import entities.*;
import input.*;
import output.*;
import utils.Constants;
import simulation.GameSimulator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public final class Main {

    private Main() {

    }

    /**
     Punctul de plecare al implementarii temei
     */
    public static void main(final String[] args) throws Exception {
//
        // Fisier de intrare
        String inFile = args[0];
        System.out.println(inFile);
        // Fisier de iesire
        String outFile = args[1];
        // Citirea datelor
        InputLoader inputLoader = new InputLoader(inFile);
        InputData inputData = inputLoader.getData();
        EntityFactory entityFactory = EntityFactory.getInstance();

        int numberOfTurns = inputData.getNumberOfTurns();

        List<ConsumerData> consumerData = inputData.getInitialData().getConsumers();
        List<DistributorData> distributorData = inputData.getInitialData().getDistributors();
        List<ProducerData> producerData = inputData.getInitialData().getProducers();

        // Creare lista de obiecte de tip Consumator
        // utilizand datele din lista de ConsumatorData
        List<Consumer> consumers = new ArrayList<>();
        for (ConsumerData c : consumerData) {
            Consumer consumer = (Consumer) entityFactory.createEntity(c, Constants.CONSUMER);
            consumers.add(consumer);
        }

//        System.out.println("######   " + consumerData.size());

        // Creare lista de obiecte de tip Distributor
        // utilizand datele din lista de DistributorData
        List<Distributor> distributors = new ArrayList<>();
        for (DistributorData d : distributorData) {
            Distributor distr = (Distributor) entityFactory.createEntity(d, Constants.DISTRIBUTOR);
            distributors.add(distr);
        }

        List<Producer> producers = new ArrayList<>();
        for (ProducerData p : producerData) {
            Producer prod = new Producer(p.getId(), p.getEnergyType(), p.getMaxDistributors(),
                                        p.getPriceKW(), p.getEnergyPerDistributor());
            producers.add(prod);
        }
        List<MonthlyUpdate> updates = inputData.getMonthlyUpdates();

//        System.out.println("consumerData" + consumerData);
//        System.out.println("distributorData" + distributorData);
//        System.out.println("monthlyUpdates" + updates);
//        System.out.println("producerData" + producerData);
//        for(ConsumerData c : consumerData) {
//            System.out.println("id " + c.getId());
//        }
//        System.out.println("----------------------------------- GREEN --------------------------------------");
//        GreenStrategy greenStrategy = new GreenStrategy(producers, 2200);
//        greenStrategy.apply();
//        System.out.println();
//        for(Producer p : producers) {
//            System.out.println("id " + p.getId() + " currDistr " + p.getCurrentDistributors()
//                    + " maxDistr " + p.getMaxDistributors());
//        }
//        System.out.println();
//
//        System.out.println("----------------------------------- PRICE --------------------------------------");
//        PriceStrategy priceStrategy = new PriceStrategy(producers, 500);
//        priceStrategy.apply();
//        System.out.println();
//
//        System.out.println("----------------------------------- QUANTITY --------------------------------------");
//        QuantityStrategy quantityStrategy = new QuantityStrategy(producers, 500);
//        quantityStrategy.apply();
//        System.out.println();

//
//        // Simularea jocului
        GameSimulator gameSimulator = new GameSimulator(numberOfTurns, consumers,
                distributors,producers ,updates);

        gameSimulator.simulateGame();
//        System.out.println("----------------------------------- PRODUCERS --------------------------------------");

//        for(Producer c : producers) {
//            System.out.println( " \nid " + c.getId() + " monthlyStats "
//                    + c.getMonthlyStats());
//        }
//        System.out.println("----------------------------------- CONSUMERS --------------------------------------");
//        for(Consumer c : gameSimulator.getConsumers()) {
//            System.out.println("id  " + c.getId() + " isBankrupt " + c.isBankrupt() + " budget " + c.getBudget());
//        }
//        System.out.println("----------------------------------- DISTRIBUTORS --------------------------------------");
//        for(Distributor c : distributors) {
//            System.out.println("\nid " + c.getId() + " energyNeededKW "
//                    + c.getEnergyNeededKW() + " contractPrice " + c.getContractPrice()
//                    + " budget " + c.getBudget()  + " producerStrategy " + c.getProducerStrategy() +
//                    " isBankrupt " + c.isBankrupt() + " contracts "  +  c.getContracts());
//        }
        //
        // Creare lista de obiecte de tip ConsumerOutput
        // utilizand datele din lista de Consumer
        List<ConsumerOutput> consumersOutput = new ArrayList<>();
        for (Consumer c : gameSimulator.getConsumers()) {
            ConsumerOutput consumerOutput = new ConsumerOutput(c.getId(), c.isBankrupt(),
                    c.getBudget());
            consumersOutput.add(consumerOutput);
        }
//
//        // Creare lista de obiecte de tip DistributorOutput
//        // utilizand datele din lista de Distributor
        List<DistributorOutput> distributorsOutput = new ArrayList<>();
        for (Distributor d : gameSimulator.getDistributors()) {
            // adaugare contracte
            List<ContractOutput> contracts = new ArrayList<>();
            for (Contract c : d.getContracts()) {
                ContractOutput contractOutput = new ContractOutput(c.getConsumerId(), c.getPrice(),
                        c.getRemainedContractMonths());
                contracts.add(contractOutput);
            }
            DistributorOutput distributorOutput = new DistributorOutput(d.getId(), d.getEnergyNeededKW(),
                                                    d.getContractPrice(),
                                                    d.getBudget(), d.getProducerStrategy(), d.isBankrupt(),
                                                    contracts);
            distributorsOutput.add(distributorOutput);
        }
        List<ProducerOutput> producerOutputs = new ArrayList<>();
        for (Producer p : gameSimulator.getProducers()) {
            List<MonthlyStatsOutput> monthlyStats = new ArrayList<>();
            for(MonthlyStats m : p.getMonthlyStats()) {
                MonthlyStatsOutput monthlyStatsOutput = new MonthlyStatsOutput(m.getMonth(), m.getDistributorsIds());
                monthlyStats.add(monthlyStatsOutput);
            }

            ProducerOutput producerOutput = new ProducerOutput(p.getId(), p.getMaxDistributors(),
                                                            p.getPriceKW(), p.getEnergyType().getLabel(),
                                                            p.getEnergyPerDistributor(),
                                                            monthlyStats);
            producerOutputs.add(producerOutput);
        }

        // Scrierea datelor
        OutputData outputData = new OutputData(consumersOutput, distributorsOutput, producerOutputs);
        OutputLoader outputLoader = new OutputLoader(outFile, outputData);
        outputLoader.putData();
    }
}
