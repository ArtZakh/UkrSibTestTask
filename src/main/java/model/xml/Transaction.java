package model.xml;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table
public class Transaction {

    public Transaction(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String place;
    private String amount;
    private String currency;
    private String card;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    public String getPlace() {
        return place;
    }

    @XmlElement
    public void setPlace(String place) {
        this.place = place;
    }

    public String getAmount() {
        return amount;
    }

    @XmlElement
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    @XmlElement
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCard() {
        return card;
    }

    @XmlElement
    public void setCard(String card) {
        this.card = card;
    }

    public Client getClient() {
        return client;
    }

    @XmlElement
    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
