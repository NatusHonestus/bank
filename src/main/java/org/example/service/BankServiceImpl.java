package org.example.service;

import org.example.DAO.BankDAO;
import org.example.model.Account;
import org.example.model.Client;
import org.example.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Andrievskiy Ilia
 */
@Service
public class BankServiceImpl implements BankService {
    private BankDAO bankDAO;

    @Autowired
    public void setBankDAO(BankDAO bankDAO){
        this.bankDAO=bankDAO;
    }

    @Transactional
    @Override
    public List<Client> getClients() {
        return bankDAO.getClients();
    }

    @Transactional
    @Override
    public List<Client> getClients(int page) {
        return bankDAO.getClients(page);
    }

    @Override
    @Transactional
    public void addClient(Client client) {
        bankDAO.addClient(client);
    }

    @Override
    @Transactional
    public void deleteClient(Client client) {
        bankDAO.deleteClient(client);
    }

    @Override
    @Transactional
    public void editClient(Client client) {
        bankDAO.editClient(client);
    }

    @Override
    @Transactional
    public Client getClient(long id) {
        return bankDAO.getClient(id);
    }

    @Override
    @Transactional
    public int clientsCount() {
        return bankDAO.clientsCount();
    }

    @Override
    public double getBalance(Account account) {
        return bankDAO.getBalance(account);
    }

    @Override
    @Transactional
    public List<Account> getAccounts() {
        return bankDAO.getAccounts();
    }

    @Override
    @Transactional
    public List<Account> getAccounts(long clients_id) {
        return bankDAO.getAccounts(clients_id);
    }

    @Override
    @Transactional
    public List<Account> getAccounts(long clients_id, int page) {
        return bankDAO.getAccounts(clients_id, page);
    }

    @Override
    @Transactional
    public void addAccount(Account account) {
        bankDAO.addAccount(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Account account) {
        bankDAO.deleteAccount(account);
    }

    @Override
    public void editAccount(Account account) {
        bankDAO.editAccount(account);
    }

    @Override
    @Transactional
    public Account getAccount(long id) {
        return bankDAO.getAccount(id);
    }

    @Override
    @Transactional
    public long accountsCount(Client client) {
        return bankDAO.accountsCount(client);
    }

    @Override
    @Transactional
    public List<Transaction> getTransactions(Client client, int page) {
        return bankDAO.getTransactions(client, page);
    }

    @Override
    @Transactional
    public List<Transaction> getTransactions(Client client, int page, String beg_date, String end_date) {
        return bankDAO.getTransactions(client, page, beg_date, end_date);
    }

    @Override
    @Transactional
    public void addTransaction(Transaction transaction) {
        bankDAO.addTransaction(transaction);
    }

    @Override
    @Transactional
    public Transaction getTransaction(long id) {
        return bankDAO.getTransaction(id);
    }

    @Override
    @Transactional
    public int transactionsCount(Client client) {
        return bankDAO.transactionsCount(client);
    }

    @Override
    @Transactional
    public int transactionsCount(Client client, String beg_date, String end_date) {
        return bankDAO.transactionsCount(client, beg_date, end_date);
    }
}
