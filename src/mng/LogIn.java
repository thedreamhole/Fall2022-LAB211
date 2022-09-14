/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

 /*Class for Log In interface -- giao dien login*/
package mng;

import data.Account;
import data.AccountChecker;
import data.Config;
import data.DealerList;
import tools.MyTool;

public class LogIn {

    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        String accName = MyTool.readNonBlank("Your account's name: ");
        String pwd = MyTool.readNonBlank("Password: ");
        String role = MyTool.readNonBlank("Your role: ");
        Account acc = new Account(accName, pwd, role);
        return acc;
    }

    public Account getAcc() {
        return this.acc;
    }

    //====Main program=====/
    public static void main(String[] args) {
        Account acc = null;//account will login to system
        boolean cont; //check to login again
        boolean valid;//valid account or not
        //log in
        do {
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            cont = false;
            if (!valid) //if above valid is false => !valid = true => execute
            {
                cont = MyTool.readBool("This account does not exist - Try again ?");
            }
            if (!valid && !cont) { //valid == false, cont = false -> !valid = true && !cont = true => true => execute 
                System.exit(0); // quit the program
            }
        } while (cont);

        LogIn loginObj = new LogIn(acc);

        //Run Dealer manager
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            //Set up menu
            String[] options = {"Add new dealer", "Search a dealer", "Remove a dealer", "Update a dealer",
                "Print all dealers", "Print continuing dealers", "Print UN-continuing dealers", "Write to file"};
            Menu menu = new Menu(options);
            DealerList dList = new DealerList(loginObj);//create a login obj for valide acc
            dList.initWithFile();
            int choice = 0;

            do {
                choice = menu.getChoice("Managing dealers");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
                        break;
                    case 5:
                        dList.printAllDealers();
                        break;
                    case 6:
                        dList.printContinuingDealers();
                        break;
                    case 7:
                        dList.printUnContinuingDealers();
                        break;
                    case 8:
                        dList.writeDealerToFile();
                        break;
                    default:
                        if (dList.isChanged()) {
                            boolean response = MyTool.readBool("Data changed. Write to file ? ");                                
                            if (response == true) {
                                dList.writeDealerToFile();
                            }
                        } 
                }
            } while (choice > 0 && choice < menu.size());
        }
    }
}
