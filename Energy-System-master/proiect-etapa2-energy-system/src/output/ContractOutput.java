package output;
/**
 * Clasa care contine datele despre un contract
 * care vor fi scrise in fisierul de iesire in format JSON
 */
public final class ContractOutput {

    private final int consumerId;
    private final int price;
    private final int remainedContractMonths;

    public ContractOutput(final int consumerId, final int price, final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public int getPrice() {
        return price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }
}
