package model.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class TransactionList {

    private List<Transaction> transactions = new ArrayList<>();


    public List<Transaction> getTransactions() {
        return transactions;
    }

    @XmlElement(name = "transaction")
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
