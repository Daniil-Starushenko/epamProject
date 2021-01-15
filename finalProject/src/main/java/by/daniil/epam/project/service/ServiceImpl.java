package by.daniil.epam.project.service;

import by.daniil.epam.project.dao.transaction.Transaction;

abstract public class ServiceImpl implements Service {
    public Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
