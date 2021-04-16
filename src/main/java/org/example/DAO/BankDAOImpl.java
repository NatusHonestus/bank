package org.example.DAO;

import org.example.model.Account;
import org.example.model.Client;
import org.example.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Andrievskiy Ilia
 */
@Repository
public class BankDAOImpl implements BankDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Client> getClients() {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery(" from Client").list();
    }

    @Override
    public List<Client> getClients(int page) {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("from Client").setFirstResult(5*(page-1)).setMaxResults(5).list();
    }

    @Override
    public void addClient(Client client) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(client);
    }

    @Override
    public void deleteClient(Client client) {
        Session session=sessionFactory.getCurrentSession();
        session.delete(client);
    }

    @Override
    public void editClient(Client client) {
        Session session=sessionFactory.getCurrentSession();
        session.update(client);
    }

    @Override
    @Transactional
    public Client getClient(long id) {
        Session session=sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }

    @Override
    public int clientsCount() {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Client ",Number.class).getSingleResult().intValue();
    }

    @Override
    public double getBalance(Account account) {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("select a.balance from Account a where a.id="+account.getId(),Number.class).getSingleResult().doubleValue();
    }

    @Override
    public List<Account> getAccounts() {
        Session session=sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cr = cb.createQuery(Account.class);
        Root root = cr.from(Account.class);
        cr.select(root);
        return session.createQuery(cr).getResultList();
    }

    @Override
    public List<Account> getAccounts(long clients_id) {
        Session session=sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cr = cb.createQuery(Account.class);
        Root root = cr.from(Account.class);
        cr.select(root).where(cb.equal(root.get("client"),clients_id));

        return session.createQuery(cr).getResultList();
    }

    @Override
    public List<Account> getAccounts(long clients_id, int page) {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("from Account a where a.client="+clients_id).setFirstResult(5*(page-1)).setMaxResults(5).list();
    }

    @Override
    @Transactional
    public void addAccount(Account account) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(account);
    }

    @Override
    public void deleteAccount(Account account) {
        Session session=sessionFactory.getCurrentSession();
        session.delete(account);
    }

    @Override
    public void editAccount(Account account) {
        Session session=sessionFactory.getCurrentSession();
        session.update(account);
    }

    @Override
    @Transactional
    public Account getAccount(long id) {
        Session session=sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }

    @Override
    public long accountsCount(Client client) {
        Session session=sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cr = cb.createQuery(Long.class);
        Root root = cr.from(Account.class);
        cr.select(cb.count(root)).where(cb.equal(root.get("client"),client));
        return session.createQuery(cr).getSingleResult();

    }

    @Override
    public List<Transaction> getTransactions(Client client, int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Transaction t where t.account_rcv IN (select a.id from Account a where a.client="+client.getId()+") " +
                " OR t.account_snd IN (select b.id from Account b where b.client="+client.getId()+")").setFirstResult(5*(page-1)).setMaxResults(5).list();

    }

    @Override
    public List<Transaction> getTransactions(Client client, int page, String beg_date, String end_date) {
        Session session = sessionFactory.getCurrentSession();
        String params="";
        if (!beg_date.isEmpty()){
            params+="and t.oper_date>='"+ Timestamp.valueOf(beg_date+" 00:00:00")+"'";
        }
        Timestamp dat2=null;
        if (!end_date.isEmpty()){
            params+="and t.oper_date<='"+Timestamp.valueOf(end_date+" 00:00:00")+"'";
        }
        return session.createQuery("from Transaction t where (t.account_rcv IN (select a.id from Account a where a.client="+client.getId()+") " +
                " OR t.account_snd IN (select b.id from Account b where b.client="+client.getId()+")) "+params).setFirstResult(5*(page-1)).setMaxResults(5).list();

    }

    @Override
    public void addTransaction(Transaction transaction) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(transaction);
    }

    @Override
    public Transaction getTransaction(long id) {
        Session session=sessionFactory.getCurrentSession();
        return session.get(Transaction.class, id);
    }

    @Override
    public int transactionsCount(Client client) {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Transaction t where t.account_rcv IN (select a.id from Account a where a.client="+client.getId()+") " +
                " OR t.account_snd IN (select b.id from Account b where b.client="+client.getId()+")", Number.class).getSingleResult().intValue();
    }

    @Override
    public int transactionsCount(Client client, String beg_date, String end_date) {
        Session session=sessionFactory.getCurrentSession();
        String params="";
        if (!beg_date.isEmpty()){
            params+="and t.oper_date>='"+Timestamp.valueOf(beg_date+" 00:00:00")+"'";
        }
        Timestamp dat2=null;
        if (!end_date.isEmpty()){
            params+="and t.oper_date<='"+Timestamp.valueOf(end_date+" 00:00:00")+"'";
        }
        return session.createQuery("select count(*) from Transaction t where (t.account_rcv IN (select a.id from Account a where a.client="+client.getId()+") " +
                " OR t.account_snd IN (select b.id from Account b where b.client="+client.getId()+")) "+params, Number.class).getSingleResult().intValue();
    }
}
