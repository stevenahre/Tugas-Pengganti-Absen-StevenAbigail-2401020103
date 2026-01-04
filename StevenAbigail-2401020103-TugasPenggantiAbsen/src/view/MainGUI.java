package view;

import controller.SalesController;
import model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGUI {
    private SalesController controller;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private JFrame frame;

    public MainGUI() {
        this.controller = new SalesController();
    }

    public void start() {
        frame = new JFrame("Sales Report");
        frame.setSize(700, 500);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = createTopPanel();
        JScrollPane scrollPane = createTablePanel();

        totalLabel = new JLabel("Total = 0");

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(totalLabel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout());
        JButton loadBtn = new JButton("Load Sales File");
        JButton generateBtn = new JButton("Generate Report");

        loadBtn.addActionListener(e -> handleLoadFile());
        generateBtn.addActionListener(e -> handleGenerateReport());

        topPanel.add(loadBtn);
        topPanel.add(generateBtn);

        return topPanel;
    }

    private JScrollPane createTablePanel() {
        String[] columns = new String[]{"ID", "Nama", "Qty", "Price"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        return scrollPane;
    }

    private void handleLoadFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            if (controller.loadSalesFile(fileChooser.getSelectedFile().getAbsolutePath())) {
                // update tabel
                updateTableView();
                // update label
                updateTotalView();
            } else {
                // show error
                showError("Gagal membaca file");
            }
        }
    }

    private void handleGenerateReport() {
        String reportFileName = "report.txt";

        if (controller.generateReport(reportFileName)) {
            JOptionPane.showMessageDialog(frame, "Laporan selesai");
        } else {
            // show error
            showError("Gagal membuat laporan");
        }
    }

    private void updateTableView() {
        tableModel.setRowCount(0);

        for (Transaction t : controller.getTransactions()) {
            tableModel.addRow(new Object[] {
                    t.getId(),
                    t.getName(),
                    t.getQuantity(),
                    t.getPrice()
            });
        }
    }

    private void updateTotalView() {
        totalLabel.setText("Total = " + controller.getGrandTotal());
    }
    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message);

    }

    public static void main(String[] args) {
        new MainGUI().start();
    }
}
