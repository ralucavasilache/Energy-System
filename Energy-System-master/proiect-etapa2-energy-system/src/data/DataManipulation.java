package data;

import entities.Consumer;
import entities.Contract;
import entities.Distributor;
import entities.EntityFactory;
import entities.MonthlyStats;
import entities.Producer;
import input.ConsumerData;
import input.DistributorData;
import input.ProducerData;
import output.ConsumerOutput;
import output.ContractOutput;
import output.DistributorOutput;
import output.MonthlyStatsOutput;
import output.ProducerOutput;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;
/**
 * Clasa care creeaza baze de date care contin obiecte folosite
 * pentru citirea datelor, pentru simularea jocului si pentru
 * scrierea datelor
 */
public final class DataManipulation {

    private final EntityFactory entityFactory = EntityFactory.getInstance();

    /**
     * Creare lista de obiecte de tip Consumator
     * utilizand datele din lista de ConsumatorData
     */
    public List<Consumer> createConsumerDB(final List<ConsumerData> consumerData) {

        List<Consumer> consumers = new ArrayList<>();

        for (ConsumerData c : consumerData) {
            Consumer consumer = (Consumer) entityFactory.createEntity(c, Constants.CONSUMER);
            consumers.add(consumer);
        }
        return consumers;
    }
    /**
     * Creare lista de obiecte de tip Distributor
     * utilizand datele din lista de DistributorData
     */
    public List<Distributor> createDistributorDB(final List<DistributorData> distributorData) {

        List<Distributor> distributors = new ArrayList<>();

        for (DistributorData d : distributorData) {
            Distributor distr = (Distributor) entityFactory.createEntity(d, Constants.DISTRIBUTOR);
            distributors.add(distr);
        }
        return distributors;
    }
    /**
     * Creare lista de obiecte de tip Producer
     * utilizand datele din lista de ProducerData
     */
    public List<Producer> createProducerDB(final List<ProducerData> producerData) {

        List<Producer> producers = new ArrayList<>();

        for (ProducerData p : producerData) {
            Producer prod = new Producer(p.getId(), p.getEnergyType(), p.getMaxDistributors(),
                    p.getPriceKW(), p.getEnergyPerDistributor());
            producers.add(prod);
        }
        return producers;
    }
    /**
     * Creare lista de obiecte de tip ConsumerOutput
     * utilizand datele din lista de Consumer
     */
    public List<ConsumerOutput> createConsumerOutputDB(final List<Consumer> consumers) {

        List<ConsumerOutput> consumersOutput = new ArrayList<>();

        for (Consumer c : consumers) {
            ConsumerOutput consumerOutput = new ConsumerOutput(c.getId(), c.isBankrupt(),
                    c.getBudget());
            consumersOutput.add(consumerOutput);
        }
        return consumersOutput;
    }
    /**
     * Creare lista de obiecte de tip DistributorOutput
     * utilizand datele din lista de Distributor
     */
    public List<DistributorOutput> createDistributorOutputDB(final List<Distributor> distributors) {

        List<DistributorOutput> distributorsOutput = new ArrayList<>();

        for (Distributor d : distributors) {
            // adaugare contracte
            List<ContractOutput> contracts = new ArrayList<>();

            for (Contract c : d.getContracts()) {
                ContractOutput contractOutput = new ContractOutput(c.getConsumerId(), c.getPrice(),
                        c.getRemainedContractMonths());
                contracts.add(contractOutput);
            }
            // Creare DistributorOutput
            DistributorOutput distributorOutput = new DistributorOutput(d.getId(),

                    d.getEnergyNeededKW(), d.getContractPrice(), d.getBudget(),
                    d.getProducerStrategy(), d.isBankrupt(), contracts);
            distributorsOutput.add(distributorOutput);
        }
        return distributorsOutput;
    }
    /**
     *  Creare lista de obiecte de tip ProducerOutput
     *  utilizand datele din lista de Producer
     */
    public List<ProducerOutput> createProducerOutputDB(List<Producer> producers) {

        List<ProducerOutput> producerOutputs = new ArrayList<>();

        for (Producer p : producers) {
            // Creare MonthlyStatsOutput
            List<MonthlyStatsOutput> monthlyStats = new ArrayList<>();
            for (MonthlyStats m : p.getMonthlyStats()) {
                MonthlyStatsOutput monthlyStatsOutput = new MonthlyStatsOutput(m.getMonth(),
                        m.getDistributorsIds());
                monthlyStats.add(monthlyStatsOutput);
            }
            // Creare ProducerOutput
            ProducerOutput producerOutput = new ProducerOutput(p.getId(), p.getMaxDistributors(),
                    p.getPriceKW(), p.getEnergyType().getLabel(), p.getEnergyPerDistributor(),
                    monthlyStats);
            producerOutputs.add(producerOutput);
        }
        return producerOutputs;
    }
}
