import input.*;
import utils.Constants;
import entities.Consumer;
import entities.Contract;
import entities.Distributor;
import entities.EntityFactory;
import output.ConsumerOutput;
import output.ContractOutput;
import output.DistributorOutput;
import output.OutputData;
import output.OutputLoader;
import simulation.GameSimulator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        // Fisier de iesire
        String outFile = args[1];
        // Citirea datelor
        InputLoader inputLoader = new InputLoader(inFile);
        InputData inputData = inputLoader.getData();

        int numberOfTurns = inputData.getNumberOfTurns();

        List<ConsumerData> consumerData = inputData.getInitialData().getConsumers();
        List<DistributorData> distributorData = inputData.getInitialData().getDistributors();
//
//
//        // Creare lista de obiecte de tip Consumator
//        // utilizand datele din lista de ConsumatorData
//        List<Consumer> consumers = new ArrayList<>();
//        for (ConsumerData c : consumerData) {
//            Consumer consumer = (Consumer) entityFactory.createEntity(c, Constants.CONSUMER);
//            consumers.add(consumer);
//        }
//
//        // Creare lista de obiecte de tip Distributor
//        // utilizand datele din lista de DistributorData
//        List<Distributor> distributors = new ArrayList<>();
//        for (DistributorData d : distributorData) {
//            Distributor distr = (Distributor) entityFactory.createEntity(d, Constants.DISTRIBUTOR);
//            distributors.add(distr);
//        }
        List<MonthlyUpdate> updates = inputData.getMonthlyUpdates();
        List<ProducerData> producerData = inputData.getInitialData().getProducers();

        System.out.println("consumerData" + consumerData);
        System.out.println("distributorData" + distributorData);
        System.out.println("monthlyUpdates" + updates);
        System.out.println("producerData" + producerData);
//
//        // Simularea jocului
//        GameSimulator gameSimulator = new GameSimulator(numberOfTurns, consumers,
//                distributors, updates);
//        gameSimulator.simulateGame();
//
//        // Creare lista de obiecte de tip ConsumerOutput
//        // utilizand datele din lista de Consumer
//        List<ConsumerOutput> consumersOutput = new ArrayList<>();
//        for (Consumer c : gameSimulator.getConsumers()) {
//            ConsumerOutput consumerOutput = new ConsumerOutput(c.getId(), c.isBankrupt(),
//                    c.getBudget());
//            consumersOutput.add(consumerOutput);
//        }
//
//        // Se sorteaza lista de distribuitori dupa id
//        Comparator<Distributor> comparator = (d1, d2) -> {
//            if (d1.getId() != d2.getId()) {
//                return d1.getId() - d2.getId();
//            }
//            return 0;
//        };
//        distributors.sort(comparator);
//
//        // Creare lista de obiecte de tip DistributorOutput
//        // utilizand datele din lista de Distributor
//        List<DistributorOutput> distributorsOutput = new ArrayList<>();
//        for (Distributor d : gameSimulator.getDistributors()) {
//            // adaugare contracte
//            List<ContractOutput> contracts = new ArrayList<>();
//            for (Contract c : d.getContracts()) {
//                ContractOutput contractOutput = new ContractOutput(c.getConsumerId(), c.getPrice(),
//                        c.getRemainedContractMonths());
//                contracts.add(contractOutput);
//            }
//            DistributorOutput distributorOutput = new DistributorOutput(d.getId(), d.getBudget(),
//                    d.isBankrupt(), contracts);
//            distributorsOutput.add(distributorOutput);
//        }
//
//        // Scrierea datelor
//        OutputData outputData = new OutputData(consumersOutput, distributorsOutput);
//        OutputLoader outputLoader = new OutputLoader(outFile, outputData);
//        outputLoader.putData();
    }
}
