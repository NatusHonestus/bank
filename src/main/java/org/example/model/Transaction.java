package org.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Andrievskiy Ilia
 */
@Entity
@Table(name="transactions")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 8988467992586606890L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name="oper_type")
    private String type;

    @Column
    private Timestamp oper_date;

    @ManyToOne
    @JoinColumn(name="account_snd")
    private Account account_snd;

    @ManyToOne
    @JoinColumn(name="account_rcv")
    private Account account_rcv;

    @Column
    private double amount;

    @Column
    private String comment;

    public Transaction(){

    }

    public Transaction(long id, String type, Timestamp oper_date, Account account_snd, Account account_rcv, double amount, String comment) {
        this.id = id;
        this.type = type;
        this.oper_date = oper_date;
        this.account_snd = account_snd;
        this.account_rcv = account_rcv;
        this.amount = amount;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", oper_date=" + oper_date +
                ", account_snd=" + account_snd +
                ", account_rcv=" + account_rcv +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount_snd() {
        return account_snd;
    }

    public void setAccount_snd(Account account_snd) {
        this.account_snd = account_snd;
    }

    public Account getAccount_rcv() {
        return account_rcv;
    }

    public void setAccount_rcv(Account account_rcv) {
        this.account_rcv = account_rcv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getOper_date() {
        return oper_date;
    }

    public void setOper_date(Timestamp oper_date) {
        this.oper_date = oper_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
