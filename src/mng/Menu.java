/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import tools.MyTool;

/**
 *
 * @author HOANGANH
 */
public class Menu extends ArrayList<String>{
   public Menu(){
       super();
   }
   public Menu(String[] items){
       super();
       for(String item : items) this.add(item);
   }
   //Get user choice
   public int getChoice(String title){ //choice from 1 to 8 . Other character refer to QUIT
        int t = -1;
        System.out.println(title);
        for (int i = 0; i < this.size(); ++i) {
            System.out.println(i+1 + " " + this.get(i));
        }
        System.out.println("  Other for quit \n" + "  Choose from " + 1 + " to " + this.size());
            try {
                System.out.print("Your choice: ");
                t = new Scanner(System.in).nextInt();
                return t;//if t is integer then automatic break and return value t;
            } catch (Exception e) {
                return t;
            }
    }
}

