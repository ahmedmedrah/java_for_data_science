package com.mycompany.cities_task;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Country {
    private String code, name,continent;
    private double population, capital;
    private double area, gpd;

    public Country(String code, String name, String continent, Double population, double area, double gpd, double capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.capital = capital;
        this.area = area;
        this.gpd = gpd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getGpd() {
        return gpd;
    }

    public void setGpd(double gpd) {
        this.gpd = gpd;
    }

    public static Country createCountry(String[] meta) {
        return new Country(meta[0].strip(), meta[1].strip(), meta[2].strip(), Double.parseDouble(meta[3]), Double.parseDouble(meta[4]), Double.parseDouble(meta[5]), Double.parseDouble(meta[6]));
    }

    public static List<Country> readFromCSV(String fileName) {
        try {
            BufferedReader buffReader;
            File file = new File(fileName);
            buffReader = new BufferedReader(new FileReader(file));

            // ArrayList as a Data Frame
            List<Country> dataSet = new ArrayList<>();

            // Read column names
            String[] columns = buffReader.readLine().split(",");
            //Read the first record
            String line = buffReader.readLine();
            // Read the file
            while(line  != null) {
                String[] record = line.split(",");
                for(String col : record) col = col.strip();
                // If there is a missing value then skip row
                if (Arrays.stream(record).noneMatch(String::isEmpty) && Double.parseDouble(record[6]) > 0){
                    // Creates City and adds it to dataSet
                    Country newObject = Country.createCountry(record);
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