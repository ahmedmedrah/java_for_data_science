package com.mycompany.cities_task;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    public static Map<String, List<City>> createCountryCodeMap(List<Country> countries, List<City> cities){
        Map<String, List<City>> codeMap = cities.stream()
                .collect(
                        Collectors.groupingBy(
                                City::getCountryCode, Collectors.toList()
                        )
                );
        return codeMap;
    }

    public static List<City> sortCitiesByPopulation(String countryCode, Map<String, List<City>> codeMap){
        return codeMap.get(countryCode)
                .stream()
                .sorted(Comparator.comparingInt(City::getPopulation)).collect(Collectors.toList());
    }
    public static void listPopulation(List<Country> countries) {
        // List of countries population
        countries.forEach(c -> System.out.println("{Country: " + c.getName() + ", Population: " + (long) c.getPopulation()));
    }

    public static void avgPopulation(List<Country> countries) {
        // List of countries population
        System.out.println("Average Population: " +
                countries.stream().collect(Collectors.averagingDouble(Country::getPopulation)).longValue());
    }

    public static void maxPopulation(List<Country> countries) {
        // List of countries population
        System.out.println("Country with max population: " +
                countries.stream().max(Comparator.comparingDouble(
                        Country::getPopulation
                )).get());
    }

    public static void highestPopulation(List<Country> countries, List<City> cities) {

        countries.forEach(c -> {
            //Filter cities of each country
            City highestCity = cities.stream()
                    .filter(x -> x.getCountryCode().equals(c.getCode())).max(Comparator.comparingInt(City::getPopulation)).get();
            // Print out result
            System.out.println("{Country: " + c.getName() + ", Highest City: " + highestCity.getName() + "}");}
        );
    }

    public static void maxPopulationCapital(List<Country> countries, List<City> cities) {
        Map<Country, City> capitalMap = new HashMap<>();
        // Map each country to its capital city (as objects)
        for(Country cntry : countries) {
            capitalMap.put(cntry, cities.get((int) cntry.getCapital()));
        }

        System.out.println(
                //Stream Each map entry
                capitalMap.entrySet().stream().max(Map.Entry.comparingByValue(
                        Comparator.comparingInt(City::getPopulation)
                )).get());
    }
}
