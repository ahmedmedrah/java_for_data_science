package com.mycompany.lamdas_and_functional_programming;

import java.util.stream.IntStream;

public class IsLetter {
    public static void main(String[] args) {
        String s = "abc1";
        String s2 = "abc";
        System.out.println(s +" is all letters? "+check(s));
        System.out.println(s2 +" is all letters? "+check(s2));

    }
    public static Boolean check(String s){
        if(s != null && !s.isEmpty()){
            IntStream chars = s.chars();
            return chars.allMatch(Character::isLetter);
        }
        return false;
    }
}
