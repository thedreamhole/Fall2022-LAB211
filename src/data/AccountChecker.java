/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import tools.MyTool;
import java.util.List;

public class AccountChecker {

    private String accFile;
    private static String SEPERATOR = ",";

    public AccountChecker() {
        setupAccFile();
    }

    private void setupAccFile() {
        accFile = new Config().getAccountFile();
    }

    //check valiadity of an account
    public boolean check(Account acc) {
        //Read data in file
        List<String> lines = MyTool.readLinesFromFile(accFile);
        //Tranverse each line for checking
        for (String line : lines) {
            String[] parts = line.split(this.SEPERATOR);
            if (parts.length < 3) {
                return false;
            }
            if (parts[0].equalsIgnoreCase(acc.getAccName()) && parts[1].equalsIgnoreCase(acc.getPwd()) && parts[2].equalsIgnoreCase(acc.getRole())) {
                return true;
            }
        }
        return false;
    }

}
