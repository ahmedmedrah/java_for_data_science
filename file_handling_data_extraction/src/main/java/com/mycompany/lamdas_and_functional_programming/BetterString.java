package com.mycompany.day3;

public class Task3 {
    public static void main(String[] args) {
        String string1 = "aaa";
        String string2 = "bbbb";
        String longer = StringUtils.betterString(string1, string2, (String s1, String s2) -> s1.length() > s2.length());
        String first = StringUtils.betterString(string1, string2, (s1, s2) -> true);

        System.out.println(longer);
        System.out.println(first);
    }


}
