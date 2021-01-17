import data.DataManipulation;
import entities.Consumer;
import entities.Distributor;
import entities.Producer;
import input.ConsumerData;
import input.DistributorData;
import input.InputData;
import input.InputLoader;
import input.MonthlyUpdate;
import input.ProducerData;
import output.ConsumerOutput;
import output.DistributorOutput;
import output.OutputData;
import output.OutputLoader;
import output.ProducerOutput;
import simulation.GameSimulator;

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

        DataManipulation dataManipulation = new DataManipulation();

        int numberOfTurns = inputData.getNumberOfTurns();

        // bazele de date in care se citesc informatiile
        List<ConsumerData> consumerData = inputData.getInitialData().getConsumers();
        List<DistributorData> distributorData = inputData.getInitialData().getDistributors();
        List<ProducerData> producerData = inputData.getInitialData().getProducers();

        // bazele de date folosite pentru simularea jocului
        List<Consumer> consumers = dataManipulation.createConsumerDB(consumerData);
        List<Distributor> distributors = dataManipulation.createDistributorDB(distributorData);
        List<Producer> producers = dataManipulation.createProducerDB(producerData);
        List<MonthlyUpdate> updates = inputData.getMonthlyUpdates();


        // simularea mecanismului
        GameSimulator gameSimulator = new GameSimulator(numberOfTurns, consumers,
                distributors, producers, updates);

        gameSimulator.simulateGame();

        // bazele de date folosite pentru scrierea datelor
        List<ConsumerOutput> consumersOutput = dataManipulation.createConsumerOutputDB(consumers);
        List<DistributorOutput> distributorsOutput = dataManipulation.createDistributorOutputDB(distributors);
        List<ProducerOutput> producerOutputs = dataManipulation.createProducerOutputDB(producers);

        // scrierea datelor
        OutputData outputData = new OutputData(consumersOutput,
                distributorsOutput, producerOutputs);
        OutputLoader outputLoader = new OutputLoader(outFile, outputData);
        outputLoader.putData();
    }
}
