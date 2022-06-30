/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package entappclient;

import ejb.DatabaseSessionRemote;
import ejb.MySessionRemote;
import java.util.ArrayList;
import java.util.Map;
import javax.ejb.EJB;

/**
 *
 * @author 37409
 */
public class Main {

    @EJB
    private static DatabaseSessionRemote databaseSession;

    @EJB
    private static MySessionRemote mySession;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String u_name = "username", c_name = "coffee name", sugar = "5", temperature = "1", id="2";
//        boolean re = databaseSession.insert(u_name, c_name, sugar, temperature);
//        boolean re = databaseSession.update(id, u_name, c_name, sugar, temperature);
//        boolean re = databaseSession.delete("2");
//        System.out.println("result = " + re);
        ArrayList<Map<String, String>> coffeeList = databaseSession.selectAll();
        for (int i=0; i<coffeeList.size(); i++){
            System.out.println(coffeeList.get(i).get("id"));
            System.out.println(coffeeList.get(i).get("u_name"));
            System.out.println(coffeeList.get(i).get("c_name"));
            System.out.println(coffeeList.get(i).get("sugar"));
            System.out.println(coffeeList.get(i).get("temperature"));
            
        }
        
    }
    
}
