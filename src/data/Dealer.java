/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import tools.MyTool;

public class Dealer implements Comparable<Dealer>{
    public static final char SEPARATOR = ',';
    public static final String ID_FORMAT = "D\\d{3}"; //Starts at letter D and followed with 3 numbers
    public static final String PHONE_FORMAT ="\\d{9}|\\d{11}";
    private String ID; //template D000
    private String name; //dealers's name
    private String addr; //dealers's address
    private String phone; //9 or 11 digits
    private boolean continuing; //whether this dealer still cooperates or not

    public Dealer(String ID, String name, String addr, String phone, boolean continuing) {
        this.ID = ID;
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.continuing = continuing;
    }
    
    public Dealer(String line){
        String[] parts = line.split("" + this.SEPARATOR);
        this.ID = parts[0].trim();
        this.name = parts[1].trim();
        this.addr = parts[2].trim();
        this.phone = parts[3].trim();
        this.continuing = MyTool.parseBool(parts[4].trim());
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isContinuing() {
        return continuing;
    }
    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }

    @Override
    public String toString() {
        return ID + SEPARATOR + name + SEPARATOR + addr + SEPARATOR + phone + SEPARATOR + continuing + "\n";
    }
    

    //Comparing tool: comparing based on their ID
    @Override
    public int compareTo(Dealer o) {
        return this.ID.compareTo(o.getID());
    }
    
    
    
    
    
    
}
