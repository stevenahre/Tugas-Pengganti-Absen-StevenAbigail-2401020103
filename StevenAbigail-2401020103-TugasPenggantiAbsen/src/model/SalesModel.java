package model;

import java.util.ArrayList;
import java.util.List;

public class SalesModel {
    private List<Transaction> transactions;

    public SalesModel() {
        this.transactions = new ArrayList<>();
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public int calculateGrandTotal() {
        return transactions.stream()
                .mapToInt(Transaction::getTotal)
                .sum();
    }

    public int getTransactionCount() {
        return transactions.size();
    }
}
