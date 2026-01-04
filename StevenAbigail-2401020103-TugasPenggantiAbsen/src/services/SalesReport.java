package services;

import model.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class SalesReport {
    public static void writeReport(String outputFile, List<Transaction> transactions) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("Sales Report");
            bw.newLine();
            LocalDateTime sekarang = LocalDateTime.now();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            bw.write("Generated at: " + sekarang.format(fmt));
            bw.newLine();
            bw.write("-------------------------");
            bw.newLine();
            bw.write(String.format(Locale.forLanguageTag("id-ID"),
                    "%-10s %-20s %8s %12s %12s",
                    "ID", "Item", "Qty", "Price", "Total"));

            bw.newLine();
            bw.write("-----------------------------------------------------------------------");
            bw.newLine();
            int total = 0;
            for (Transaction t: transactions) {
                bw.write(String.format(Locale.forLanguageTag("id-ID"),
                        "%-10s %-20s %8d %12d %12d",
                        t.getId(), t.getName(), t.getQuantity(), t.getPrice(), t.getTotal()));
                total += t.getTotal();
                bw.newLine();

            }
            bw.newLine();
            bw.write("-----------------------------------------------------------------------");
            bw.newLine();

            bw.write("Grand total = " + total);

        }
    }
}
