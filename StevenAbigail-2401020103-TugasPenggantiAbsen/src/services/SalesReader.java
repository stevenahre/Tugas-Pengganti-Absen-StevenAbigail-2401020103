package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SalesReader {
    public List<String> readFromFile(String filename) {
        List<String> output = new ArrayList<String>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
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
