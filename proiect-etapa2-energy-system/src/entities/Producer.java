package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Producer extends Observable {
    private final int id;
    private EnergyType energyType;
    private final int maxDistributors;
    private int currentDistributors = 0;
    private final double priceKW;
    private  int energyPerDistributor;
    private List<MonthlyStats> monthlyStats;
    //private List<Integer> monthlyDistributorIds;
//    private List<Observer> observers;

    public Producer(int id, String energyType, int maxDistributors, double priceKW, int energyPerDistributor) {
        this.id = id;
        switch (energyType) {
            case "WIND" -> this.energyType = EnergyType.WIND;
            case "SOLAR" -> this.energyType = EnergyType.SOLAR;
            case "HYDRO" -> this.energyType = EnergyType.HYDRO;
            case "COAL" -> this.energyType = EnergyType.COAL;
            case "NUCLEAR" -> this.energyType = EnergyType.NUCLEAR;
        }
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        monthlyStats = new ArrayList<>();
        //monthlyDistributorIds = new ArrayList<>();
        //observers = new ArrayList<>();
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
        setChanged();
//        System.out.println(countObservers());
        notifyObservers();
    }

    public int getId() {
        return id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public int getCurrentDistributors() {
        return currentDistributors;
    }
    public void addDistributor() {
        this.currentDistributors++;
    }
    public void addId(int id, int month) {
//        System.out.println("month   " + month);
        monthlyStats.get(month).getDistributorsIds().add(id);
    }
    public void addMonthlyState(int month) {
        MonthlyStats newMonth = new MonthlyStats(month+1);
        monthlyStats.add(newMonth);
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public void setCurrentDistributors(int currentDistributors) {
        this.currentDistributors = currentDistributors;
    }

    public List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

//    public List<Integer> getMonthlyDistributorIds() {
//        return monthlyDistributorIds;
//    }

//    public void setMonthlyDistributorIds(List<Integer> monthlyDistributorIds) {
//        this.monthlyDistributorIds = monthlyDistributorIds;
//    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
//        System.out.println(observers);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                '}';
    }
    public void decCurrentDistrb() {
        currentDistributors--;
    }

}
