package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalesReader {
    public List<String> readFromFile(String filename) {
        List<String> output = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.trim().startsWith("#"))
                    output.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        return output;
    }
}
