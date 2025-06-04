package it.unicam.cs.mpgc.jbudget125914;

import it.unicam.cs.mpgc.jbudget125914.model.dao.GenericDao;
import it.unicam.cs.mpgc.jbudget125914.model.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.model.entities.category.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.FinancialTransaction;

import java.util.List;

public class App {

    public static void main(String[] args) {

        GenericDao<FinancialTag> tagDao = new GenericDao<>(FinancialTag.class);
        GenericDao<FinancialAccount> accountDao = new GenericDao<>(FinancialAccount.class);
        GenericDao<FinancialTransaction> transactionDao = new GenericDao<>(FinancialTransaction.class);

        List<FinancialTag> tags = tagDao.findAll();
        List<FinancialAccount> accounts = accountDao.findAll();
        List<FinancialTransaction> transactions = transactionDao.findAll();

        tags.forEach(System.out::println);
        accounts.forEach(System.out::println);
        transactions.forEach(System.out::println);


    }
}
