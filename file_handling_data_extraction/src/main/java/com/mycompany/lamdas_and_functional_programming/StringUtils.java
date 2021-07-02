package com.mycompany.lamdas_and_functional_programming;

import java.util.function.BiPredicate;

public class StringUtils {
    public static String betterString(String s1, String s2, BiPredicate<String, String> f){
        if(f.test(s1,s2))
            return s1;
        return s2;
    }
}
