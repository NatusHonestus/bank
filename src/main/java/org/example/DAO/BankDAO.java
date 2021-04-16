package org.example.DAO;

import org.example.model.Account;
import org.example.model.Client;
import org.example.model.Transaction;

import java.util.List;

/**
 * @author Andrievskiy Ilia
 */
public interface BankDAO {
    List<Client> getClients();
    List<Client> getClients(int page);
    void addClient(Client client);
    void deleteClient(Client client);
    void editClient(Client client);
    Client getClient(long id);
    int clientsCount();
    double getBalance(Account account);

    List<Account> getAccounts();
    List<Account> getAccounts(long clients_id);
    List<Account> getAccounts(long clients_id, int page);

    void addAccount(Account account);
    void deleteAccount(Account account);
    void editAccount(Account account);
    Account getAccount(long id);
    long accountsCount(Client client);

    List<Transaction> getTransactions(Client client, int page);
    List<Transaction> getTransactions(Client client, int page, String beg_date, String end_date);
    void addTransaction(Transaction transaction);
    Transaction getTransaction(long id);
    int transactionsCount(Client client);
    int transactionsCount(Client client, String beg_date, String end_date);
}
