package view;

import controller.SalesController;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the "D:\\Dosen\\2025\\PBO2025\\Pertemuan 10\\Sales\\src\\sales.txt"<icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private SalesController controller;

    public Main() {
        this.controller = new SalesController();
    }

    public void start() {
        if (controller.loadSalesFile("src/sales.txt"))
        {
            controller.generateReport("report.txt");
        }

    }

    public static void main(String[] args) throws IOException {
        new Main().start();
    }
}