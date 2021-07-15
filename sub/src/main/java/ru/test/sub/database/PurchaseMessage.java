package ru.test.sub.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "PURCHASE")
public class PurchaseMessage {
    @Id
    private Long id;

    private int msisdn;
    private Timestamp timestamp;

    public PurchaseMessage(Long id, int msisdn, Timestamp timestamp) {
        this.id = id;
        this.msisdn = msisdn;
        this.timestamp = timestamp;
    }

    public PurchaseMessage() { }


    public Long getId() {
        return id;
    }

    public int getMsisdn() {
        return msisdn;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMsisdn(int msisdn) {
        this.msisdn = msisdn;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
