package entity;

import java.util.Date;

public class Transaction {
    private TransactionType transactionType;
    private double amount;
    private Customer issuingCustomer;
    private Customer issuedCustomer;
    private Date date;
    private double previousBalance;
    private double afterBalance;

    public Transaction(TransactionType transactionType, double amount, Date date, Customer issuingCustomer, Customer issuedCustomer) {
        this.amount = amount;
        this.previousBalance = transactionType == TransactionType.EARNING ? issuedCustomer.getBalance() : issuingCustomer.getBalance();
        this.afterBalance = transactionType == TransactionType.EXPENSE ? this.previousBalance - amount : this.previousBalance + amount;
        this.transactionType = transactionType;
        this.date = date;
        this.issuingCustomer = issuingCustomer;
        this.issuedCustomer = issuedCustomer;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public Customer getIssuingCustomer() {
        return issuingCustomer;
    }

    public Customer getIssuedCustomer() {
        return issuedCustomer;
    }

    public Date getDate() {
        return date;
    }

    public double getPreviousBalance() {
        return previousBalance;
    }

    public double getAfterBalance() {
        return this.afterBalance;
    }

    public enum TransactionType {
        RECHARGE,
        EARNING,
        EXPENSE
    }
}
