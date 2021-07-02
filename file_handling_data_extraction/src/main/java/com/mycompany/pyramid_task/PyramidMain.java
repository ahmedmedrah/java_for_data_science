package com.mycompany.pyramid_task;

import java.util.*;
import java.util.stream.Collectors;

public class PyramidMain {
    public static void main(String[] args) {
        PyramidDao p = new PyramidCSVDAO();

        List<Pyramid> ps = p.readPyramidFromCSV("pyramids.csv");

        // ---- Built-in Data Structures ----

        System.out.println("list of pyramids sorted by height descending order");
        ps.sort(Comparator.comparingDouble(Pyramid::getHeight).reversed());
        ps.forEach(System.out::println);
        System.out.println("-----------------------------------------------------");

        // ---- Stream API --------
        System.out.println("List of location and number of pyramids in descending order");
        Map<String, Long> sites = ps.stream().collect(Collectors.groupingBy(Pyramid::getSite, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        sites.forEach((k, v) -> System.out.println(k + " " + v));


        System.out.println("\nthe tallest pyramid:");
        System.out.println(ps.get(0));

    }
}
