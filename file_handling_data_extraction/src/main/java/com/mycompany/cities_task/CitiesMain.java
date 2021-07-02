package com.mycompany.cities_task;

import com.mycompany.pyramid_task.Pyramid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CitiesMain
{
    public static void main(String[] args) throws IOException {

        // read the data form CSV
        List<City> cities = City.readFromCSV("Cities.csv");
        List<Country> countries = Country.readFromCSV("Countries.csv");

        // create a map with country code as key and list of cities as values
        Map<String, List<City>> codeMap = Utils.createCountryCodeMap(countries,cities);
        //codeMap.forEach((k, v) -> System.out.println("{Key: " + k + ", value: " + v + "}\n"));

        // read a country code from console
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader buffReader = new BufferedReader(reader);
        System.out.println("Enter Country Code:");
        String userInput = buffReader.readLine();

        // get the cities associated with that country code, then sort them by population
        List<City> sortedCities = Utils.sortCitiesByPopulation(userInput, codeMap);
        System.out.println("\nCities in "+userInput+": ");
        sortedCities.forEach(System.out::println);

        System.out.println("--------------------------------------------------------");
        // print the capital with maximum population
        System.out.println("\nthe capital with maximum population: ");
        Utils.maxPopulationCapital(countries, cities);

        System.out.println("--------------------------------------------------------");
        // print the highest population city per country
        System.out.println("\nthe highest population city per country: ");
        Utils.highestPopulation(countries, cities);

        System.out.println("--------------------------------------------------------");
        // print the maximum population country
        System.out.println("\nthe maximum population country: ");
        Utils.maxPopulation(countries);

        System.out.println("--------------------------------------------------------");
        // print the average country population
        System.out.println("\nthe average country population: ");
        Utils.avgPopulation(countries);

        System.out.println("--------------------------------------------------------");
        // print a list of countries and its population
        System.out.println("\nlist of each country and its population: ");
        Utils.listPopulation(countries);
    }
}
