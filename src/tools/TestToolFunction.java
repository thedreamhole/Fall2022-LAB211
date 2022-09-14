package tools;

import data.Account;
import data.AccountChecker;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import mng.LogIn;
import static mng.LogIn.inputAccount;
import static tools.MyTool.SC;
import static tools.MyTool.validStr;

/**
 *
 * @author HOANGANH
 */
public class TestToolFunction {

    public static void main(String[] args) {
        String accountFile = "";
        String dealerFile = "";
        String deliveryFile = "";
        List<String> lines = MyTool.readLinesFromFile("DealerData\\config.txt");
        for (String line : lines) {
            line = line.toUpperCase();
            String[] parts = line.split(":");
            if (line.indexOf("ACCOUNT") >= 0) {
                accountFile = "DealerData\\" + parts[1].trim();
            } else if (line.indexOf("DEALE") >= 0) {
                dealerFile = "DealerData\\" + parts[1].trim();
            } else if (line.indexOf("DELIVER") >= 0) {
                deliveryFile = "DealerData\\" + parts[1].trim();
            }
        }

        System.out.println(accountFile);
        System.out.println(dealerFile);
        System.out.println(deliveryFile);

        Account acc = null;//account will login to system
        boolean cont = false; //check to login again
        boolean valid = false;//valid account or not
        
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
           System.out.println(acc.getAccName());
           System.out.println(acc.getPwd());
           System.out.println(acc.getRole());
           System.out.println();
           
         List<String> lineacc = MyTool.readLinesFromFile(accountFile);
         for(String i :  lineacc){
             String[] parts = i.split(",");
   
             System.out.println(parts[0] + " " + parts[0].equalsIgnoreCase(acc.getAccName()));
             System.out.println(parts[1] + " " + parts[1].equalsIgnoreCase(acc.getPwd()));
             System.out.println(parts[2] + " " + parts[2].equalsIgnoreCase(acc.getRole()));
             
             System.out.println(parts[0].equalsIgnoreCase(acc.getAccName()) && parts[1].equalsIgnoreCase(acc.getPwd()) && parts[2].equalsIgnoreCase(acc.getRole()));
         }
        
    }
}
