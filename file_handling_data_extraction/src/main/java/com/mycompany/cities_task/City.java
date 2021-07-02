package com.mycompany.cities_task;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class City {
    private int id, population;
    private String name, countryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public City(int id, int population, String name, String countryCode) {
        this.id = id;
        this.population = population;
        this.name = name;
        this.countryCode = countryCode;
    }

    public static City createCity(String[] meta) {
        return new City(Integer.parseInt(meta[0]), Integer.parseInt(meta[2]), meta[1].strip(), meta[3].strip());
    }

    public static List<City> readFromCSV(String fileName) {
        try {
            BufferedReader buffReader;
            File file = new File(fileName);
            buffReader = new BufferedReader(new FileReader(file));

            // ArrayList as a Data Frame
            List<City> dataSet = new ArrayList<>();

            // Read column names
            String[] columns = buffReader.readLine().split(",");
            //Read the first record
            String line = buffReader.readLine();
            // Read the file
            while(line  != null) {
                String[] record = line.split(",");
                // If there is a missing value then skip row
                if (Arrays.stream(record).noneMatch(String::isEmpty)){
                    // Creates City and adds it to dataSet
                    City newObject = City.createCity(record);
                    dataSet.add(newObject);
                    line = buffReader.readLine();
                    continue;
                }
                line = buffReader.readLine();
            }
            buffReader.close();
            return dataSet;
        }
        catch(FileNotFoundException fileEx) {
            System.out.println("File exception");
            return null;
        }
        catch(IOException ioEx) {
            System.out.println("IO Exception");
            return null;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
