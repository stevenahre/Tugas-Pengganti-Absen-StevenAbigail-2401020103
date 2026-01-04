package controller;

import model.SalesModel;
import model.Transaction;
import services.SalesReader;
import services.SalesReport;
import services.TransactionProcessor;

import java.io.IOException;
import java.util.List;

public class SalesController {
    private SalesModel model;
    private SalesReader reader;
    private TransactionProcessor processor;
    private SalesReport report;

    public SalesController() {
        this.model = new SalesModel();
        this.reader = new SalesReader();
        this.processor = new TransactionProcessor();
        this.report = new SalesReport();
    }

    public boolean loadSalesFile(String filename) {
        try {
            List<String> salesData = reader.readFromFile(filename);
            List<Transaction> transactions = processor.parseSalesData(salesData);
            model.setTransactions(transactions);
            return true;
        } catch (Exception e) {
            System.err.println("Error loading file " + e.getMessage());
            return false;
        }
    }

    public List<Transaction> getTransactions() {
        return model.getTransactions();
    }

    public int getGrandTotal() {
        return model.calculateGrandTotal();
    }

    public boolean generateReport(String outputFile) {
        try {
            SalesReport.writeReport(outputFile, getTransactions());
            return true;
        } catch (IOException e) {
            System.err.println("Error generating report " + e.getMessage());
            return false;
        }
    }
}
