/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.read_write_from_user;

import java.io.*;

/**
 * @author Ahmed Medra
 */
public class Application {
    public static void main(String[] args) {
        File f = new File("in.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        try {
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String in;
            do {
                System.out.println("Enter a word:");
                in = bufferedReader.readLine();
                System.out.println("word is " + in);
                bufferedWriter.write(in + '\n');
            }
            while (!in.equalsIgnoreCase("stop"));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
