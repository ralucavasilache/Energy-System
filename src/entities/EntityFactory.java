package entities;

import utils.Constants;
import input.ConsumerData;
import input.DistributorData;

public final class EntityFactory {

    /**
     * Singleton Pattern
     */
    private static EntityFactory instance = null;

    private EntityFactory() {

    }
    /**
     * Permite instantierea clasei EntityFactory o singura data
     */
    public static EntityFactory getInstance() {
        if (instance == null) {
            instance = new EntityFactory();
        }
        return instance;
    }
    /**
     * Creeaza un obiect de tipul Distributor sau Consumer
     * @param entity, obiectul pe baza caruia se creeaza o entitate noua
     * @param type, tipul de entitate care va fi creata (distributor sau consumer)
     * @return Entity, noua entitate de tipul mentionat
     */
    public Entity createEntity(final Object entity, final String type) {
        if (type.equals(Constants.DISTRIBUTOR)) {

            DistributorData distributor = ((DistributorData) entity);
            return new Distributor(distributor.getId(), distributor.getContractLength(),
                                    distributor.getInitialBudget(),
                                    distributor.getInitialInfrastructureCost(),
                                    distributor.getEnergyNeededKW(),
                                    distributor.getProducerStrategy());
        } else if (type.equals(Constants.CONSUMER)) {

            ConsumerData consumer = ((ConsumerData) entity);
            return new Consumer(consumer.getId(), consumer.getInitialBudget(),
                                consumer.getMonthlyIncome());
        }
        throw new IllegalArgumentException("The entity " + type + " does not exist!");
    }
}
