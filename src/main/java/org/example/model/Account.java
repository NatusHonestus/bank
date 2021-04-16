package org.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Andrievskiy Ilia
 */
@Entity
@Table(name="accounts")
public class Account implements Serializable {
    private static final long serialVersionUID = -8338932711343859521L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String number;

    @Column
    private double balance;

    @ManyToOne
    @JoinColumn(name="clients_id")
    private Client client;

    @OneToMany(mappedBy="account_snd", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Transaction> account_snd;

    @OneToMany(mappedBy="account_rcv", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Transaction> account_rcv;

    public Account(){

    }

    public Account(long id, Client client, String number, double balance) {
        this.id = id;
        this.client = client;
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClients_id() {
        return client;
    }

    public void setClients_id(Client client) {
        this.client = client;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
