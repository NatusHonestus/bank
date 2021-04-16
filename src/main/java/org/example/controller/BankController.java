package org.example.controller;

import org.example.model.Account;
import org.example.model.Client;
import org.example.model.Transaction;
import org.example.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.apache.taglibs.standard.functions.Functions.trim;

/**
 * @author Andrievskiy Ilia
 */
@Controller
public class BankController {
    private BankService bankService;
    private int page;

    @Autowired
    public void setBankService(BankService bankService){
        this.bankService=bankService;
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView mainPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/clients");
        return modelAndView;
    }

    @Transactional
    @RequestMapping(value="/clients", method= RequestMethod.GET)
    public ModelAndView getClients(@RequestParam(defaultValue="1") int page){
        this.page=page;
        List<Client> clients=bankService.getClients(page);
        int clientsCount=bankService.clientsCount();
        int pagesCount=(clientsCount+4)/5;
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("clients");
        modelAndView.addObject("clients", clients);
        modelAndView.addObject("page", page);
        modelAndView.addObject("clientsCount", clientsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }

    @RequestMapping(value="/editClient/{id}",method = RequestMethod.GET)
    public ModelAndView addClient(@PathVariable("id") long id){
        Client client=bankService.getClient(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addClient");
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @RequestMapping(value="/editClient", method=RequestMethod.POST)
    public ModelAndView editClient(@ModelAttribute("client") Client client){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/clients?page="+this.page);
        bankService.editClient(client);
        return modelAndView;
    }

    @RequestMapping(value="/addClient", method=RequestMethod.GET)
    public ModelAndView newClientPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addClient");
        return modelAndView;
    }

    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public ModelAndView addClient(@ModelAttribute("client") Client client){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/clients?page="+this.page);
        bankService.addClient(client);
        return modelAndView;
    }

    @RequestMapping(value="/deleteClient/{id}", method=RequestMethod.GET)
    public ModelAndView deleteClient(@PathVariable("id") long id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/clients?page="+this.page);
        Client client=bankService.getClient(id);
        bankService.deleteClient(client);
        return modelAndView;
    }

    @Transactional
    @RequestMapping(value="/accounts/{id}", method= RequestMethod.GET)
    public ModelAndView getAccounts(@PathVariable("id") long clients_id, @RequestParam(defaultValue = "1") int page){
        this.page=page;
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("accounts");
        List<Account> accounts=bankService.getAccounts(clients_id, page);
        Client client=bankService.getClient(clients_id);
        long accountsCount=bankService.accountsCount(client);
        long pagesCount=(accountsCount+4)/5;
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("page", page);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("accountsCount", accountsCount);
        modelAndView.addObject("clients_id", clients_id);
        return modelAndView;
    }

    @Transactional
    @RequestMapping(value="/addAccount/{id}", method=RequestMethod.GET)
    public ModelAndView newAccountPage(@PathVariable("id") long clients_id){
        ModelAndView modelAndView=new ModelAndView();
        Client client=bankService.getClient(clients_id);
        modelAndView.addObject("client",client);
        modelAndView.setViewName("addAccount");
        return modelAndView;
    }

    @Transactional
    @RequestMapping(value="/addAccount", method=RequestMethod.POST)
    public ModelAndView addAccount(@ModelAttribute("Account") Account account, @RequestParam("clientsid") long clientsid){
        Client client=bankService.getClient(clientsid);
        account.setClients_id(client);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/accounts/"+clientsid+"?page="+this.page);
        bankService.addAccount(account);
        return modelAndView;
    }

    @Transactional
    @RequestMapping(value="/deleteAccount/{id}", method=RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable("id") long account_id){
        ModelAndView modelAndView=new ModelAndView();
        Account account=bankService.getAccount(account_id);
        modelAndView.setViewName("redirect:/accounts/"+account.getClients_id().getId()+"?page="+this.page);
        bankService.deleteAccount(account);
        return modelAndView;
    }



    @Transactional
    @RequestMapping(value="/transactions/{id}", method= RequestMethod.GET)
    public ModelAndView getTransactions(@PathVariable("id") long clients_id, @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "") String beg_date, @RequestParam(defaultValue = "") String end_date){
        this.page=page;
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("transactions");
        List<Client> clients=bankService.getClients();
        modelAndView.addObject("clients",clients);
        Client client=bankService.getClient(clients_id);
        List<Transaction> transactions=bankService.getTransactions(client, this.page, beg_date, end_date);
        long transactionsCount=bankService.transactionsCount(client, beg_date, end_date);
        long pagesCount=(transactionsCount+4)/5;
        modelAndView.addObject("page", page);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("transactionsCount", transactionsCount);
        modelAndView.addObject("transactions", transactions);
        modelAndView.addObject("clients_id", clients_id);
        return modelAndView;
    }

    @Transactional
    @RequestMapping(value="/addTransaction/{id}", method=RequestMethod.GET)
    public ModelAndView newTransactionPage(@PathVariable("id") long clients_id){
        ModelAndView modelAndView=new ModelAndView();
        Client client=bankService.getClient(clients_id);
        modelAndView.addObject("client",client);
        List<Account> owner_accounts=bankService.getAccounts(clients_id);
        modelAndView.addObject("owner_accounts",owner_accounts);
        List<Account> accounts=bankService.getAccounts();
        modelAndView.addObject("accounts",accounts);
        String timestamp=new Timestamp(new Date().getTime()).toString();
        modelAndView.addObject("oper_date",timestamp.substring(0,19));
        modelAndView.setViewName("addTransaction");
        return modelAndView;
    }

    @Transactional
    @RequestMapping(value="/addTransaction", method=RequestMethod.POST)
    public ModelAndView addTransaction(@ModelAttribute("Transaction") Transaction transaction, @RequestParam("accounts_snd") String accounts_snd,
                                       @RequestParam("accounts_rcv") String accounts_rcv, @RequestParam("operdate") String operdate,
                                       @RequestParam("clientsid") long clientsid) {
        Client client = bankService.getClient(clientsid);
        ModelAndView modelAndView=new ModelAndView();
        Double amount=transaction.getAmount();
        transaction.setType("r");
//        Account account=bankService.getAccount(Long.parseLong(accounts_snd));
        if (!accounts_snd.isEmpty()){
            Account acc_snd = bankService.getAccount(Long.parseLong(trim(accounts_snd)));
            Double bal = bankService.getBalance(acc_snd);
            if (bal<transaction.getAmount()){
                String mess = "недостаточно средств";
                modelAndView.setViewName("addTransaction");
                modelAndView.addObject("snd_mess",mess);
                modelAndView.addObject("client",client);
                List<Account> owner_accounts=bankService.getAccounts(clientsid);
                modelAndView.addObject("owner_accounts",owner_accounts);
                List<Account> accounts=bankService.getAccounts();
                modelAndView.addObject("accounts",accounts);
                String timestamp=new Timestamp(new Date().getTime()).toString();
                modelAndView.addObject("oper_date",timestamp.substring(0,19));
                return modelAndView;
            }
            transaction.setAccount_snd(acc_snd);
            Double snd_bal=acc_snd.getBalance();
            acc_snd.setBalance(snd_bal-amount);
            bankService.editAccount(acc_snd);
            transaction.setType("w");
        }

        if (accounts_rcv.isEmpty()){
            String mess = "необходимо указать счет получателя";
            modelAndView.setViewName("addTransaction");
            modelAndView.addObject("snd_mess",mess);
            modelAndView.addObject("client",client);
            List<Account> owner_accounts=bankService.getAccounts(clientsid);
            modelAndView.addObject("owner_accounts",owner_accounts);
            List<Account> accounts=bankService.getAccounts();
            modelAndView.addObject("accounts",accounts);
            String timestamp=new Timestamp(new Date().getTime()).toString();
            modelAndView.addObject("oper_date",timestamp.substring(0,19));
            return modelAndView;
        }


        Account acc_rcv=bankService.getAccount(Long.parseLong(trim(accounts_rcv)));

        transaction.setAccount_rcv(acc_rcv);
        Timestamp timestamp=Timestamp.valueOf(operdate);
        transaction.setOper_date(timestamp);
        bankService.addTransaction(transaction);


        Double rcv_bal=acc_rcv.getBalance();
        acc_rcv.setBalance(rcv_bal+amount);
        bankService.editAccount(acc_rcv);

        modelAndView.setViewName("redirect:/transactions/"+clientsid+"?page="+this.page);
        return modelAndView;
    }
}
