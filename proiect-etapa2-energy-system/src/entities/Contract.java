package entities;
/**
 Clasa contine campurile si metodele specifice unui contract
 */
public final class Contract {
    /**
     Id-ul consumatorului
     */
    private int consumerId;
    /**
     Pretul contractului
     */
    private int price;
    /**
     Lunile ramase din contract
     */
    private int remainedContractMonths;

    public Contract(final int consumerId, final int price, final int remainedContractMonths) {

        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
    /**
     * Decrementeaza numarul de luni din contract ramase
     */
    public void decrementRemainedContractMonths() {
        remainedContractMonths--;
    }
}
