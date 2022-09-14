/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author HOANGANH
 */
public class MyTool {

    public static final Scanner SC = new Scanner(System.in);

    //checking whether str matches a pattern or not
    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    //Checking a password with minLen, contain at least a character + a number + a specific character
    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        } else {
            return str.matches(".*[a-zA-Z]+.*")
                    && //contain at least 1 character
                    str.matches(".*[\\d]+.*")
                    && //contain at least 1 number
                    str.matches(".*[\\W]+.*"); //contain at least 1 specific character
        }
    }
    //Date format: yyyy/MM/dd, MM/dd/yyyy, dd/MM, yyyy,...
    //Date string will be changed to a valid date value automatically

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    //Use the class SimpleDateFormat
    //Use the method applyPattern(str) to apply a specific format
    //Use the method format(date) to convert date -> String
    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        return dF.format(date);
    }

    public static boolean parseBool(String boolStr) {      
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' | c == 'Y' | c == 'T');
    }

    //Tools for inputting data
    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message);
            input = SC.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message);
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid); //if valid below = true => !true = false => out loop  else valid = false => !false = true => loop again               
        return input;
    }

    public static boolean readBool(String message) {
        String input = "";
        do {
            System.out.print(message + " Enter one character only [1/0-Y/N-T/F] : ");
            input = SC.nextLine().trim(); 
        } while(input.isEmpty() || input.length() > 1 || !input.matches("[10YNTF]"));
        return parseBool(input);
    }

    public static List<String> readLinesFromFile(String filename) {
        List list = new ArrayList<String>();
        try {
            FileReader fileread = new FileReader(filename); //read()
            BufferedReader bufread = new BufferedReader(fileread); //readLine()
            String line = "";
            while ((line = bufread.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    list.add(line);
                }
            }
            bufread.close();
            fileread.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public static void writeFile(String filename, List list) {
        if (list.size() == 0) {
            System.out.println("Empty list");
            return;
        }
        try {
            File f = new File(filename); //check file
            FileWriter filewrite = new FileWriter(f); //write
            BufferedWriter bufwrite = new BufferedWriter(filewrite);
            for (int i = 0; i < list.size(); ++i) {
                bufwrite.write(list.get(i).toString());
            }
           bufwrite.close(); filewrite.close(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
