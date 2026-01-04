package services;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionProcessor {
    public List<Transaction> parseSalesData(List<String> salesData) {

        List<Transaction> output = new ArrayList<>();
        for (String salesLine : salesData) {
            String[] parts = salesLine.split(",");
            Transaction transaction = new Transaction(parts[0].trim(), parts[1].trim(), Integer.parseInt(parts[2].trim()), Integer.parseInt(parts[3].trim()));

            output.add(transaction);
        }

        return output;
    }
}
