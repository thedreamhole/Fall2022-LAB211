/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;
import tools.MyTool;
import mng.LogIn;

public class DealerList extends ArrayList<Dealer> {
    LogIn loginObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false; //whether data in the list changed or not
    
    public DealerList(LogIn loginObj){
        this.loginObj = loginObj;
    }
    //*load dealers from file
    private void loadDealerFromFile() {
        List lines = null;
        lines = MyTool.readLinesFromFile(dataFile);
        for (int i = 0; i < lines.size(); ++i) {
            Dealer dealerfromfile = new Dealer(lines.get(i).toString());
            this.add(dealerfromfile);            
        }
    }
    
    public void initWithFile(){
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }
    
    public DealerList getContinuingList(){
        DealerList resultlist = new DealerList(this.loginObj);
        for(int i = 0; i < this.size(); ++i){
            Dealer d = this.get(i);
            if(d.isContinuing() == true){
                resultlist.add(d);
            }
        }
        return resultlist;
    }
    
    public DealerList getUnContinuingList() {
        DealerList resultlist = new DealerList(this.loginObj);
        for (int i = 0; i < this.size(); ++i) {
            Dealer d = this.get(i);
            if (d.isContinuing() == false) {
                resultlist.add(d);
            }
        }
        return resultlist;
    }
    
    public int searchDealer(String ID){        
        String keyID = ID.toUpperCase().trim();
        for(int i = 0 ; i < this.size(); ++i){
            if(keyID.equals(this.get(i).getID())) return i;
        }
        return -1;
    }
    
    public void searchDealer(){     
        System.out.print("ID key: ");
        String keyID = MyTool.SC.nextLine().trim();
        int pos = this.searchDealer(keyID);        
        if(pos < 0) System.out.println("NOT FOUND!");
        else System.out.println(this.get(pos));
    }
    
    //Add Deaeler
    public void addDealer(){
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        do{ //first check Does ID input is repeated or invalid
            ID = MyTool.readPattern("ID of new dealer: ", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if(pos >= 0) System.out.println("ID is duplicated !");
        }while(pos>= 0);
        name = MyTool.readNonBlank("Name of new dealer: ").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer: ");
        phone = MyTool.readPattern("Phone number: ", Dealer.PHONE_FORMAT);
        continuing = true; //default value for new dealer
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added.");
        changed = true;
    }
    
    public void removeDealer() {
        System.out.print("ID key: ");
        String keyID = MyTool.SC.nextLine().trim().toUpperCase();
        int pos = this.searchDealer(keyID);
        if (pos < 0) {
            System.out.println("Not found");
        } else {
            this.get(pos).setContinuing(false);
            System.out.println("Removed");
            changed = true; //data changed
        }

        //update a dealer
        //Only name; addr and phone can be changed
    }
    public void updateDealer() {
        String newName = "";
        String newaddr = "";
        String newphone = "";
        System.out.print("Dealer's ID needs updating: ");
        String ID = MyTool.SC.nextLine().trim();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found!");
        } else {
            Dealer d = this.get(pos);
            //update name            
            System.out.print("new name, ENTER for omitting: ");
            newName = MyTool.SC.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }
            //update addr            
            System.out.print("new address, ENTER for omitting: ");
            newaddr = MyTool.SC.nextLine().trim();
            if (!newaddr.isEmpty()) {
                d.setAddr(newaddr);
                changed = true;
            }
            //update phone  

            boolean cont = false;
            do {
                System.out.print("new phone, ENTER for omitting: ");

                newphone = MyTool.SC.nextLine().trim();

                if (!newphone.isEmpty()) {
                    if (MyTool.validStr(newphone, PHONEPATTERN)) {
                        d.setPhone(newphone);
                        cont = true;
                        changed = true;
                    } else {
                        System.out.println("Invalid Phone number");
                    }
                }
            } while (!cont);
        }
    }
    //Print all dealers
    public void printAllDealers(){
        if(this.isEmpty()) System.out.println("Empty List !");
        else System.out.println(this);
    }
    //Print all continuing dealers
    public void printContinuingDealers(){
        this.getContinuingList().printAllDealers();
    }
    //Print all uncontinuing dealers
    public void printUnContinuingDealers(){
        this.getUnContinuingList().printAllDealers();
    }
    
    //Write dealer list to file
    public void writeDealerToFile(){
        if(changed){
            MyTool.writeFile(dataFile, this);
            changed = false; //sau khi changed xong return default value of changed
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    
    
    

        
        
       
    
    
}
