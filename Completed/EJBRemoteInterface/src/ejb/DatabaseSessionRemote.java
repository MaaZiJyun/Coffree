/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb;

import java.util.ArrayList;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author 37409
 */
@Remote
public interface DatabaseSessionRemote {

    public boolean insert(String u_name, String c_name, String sugar, String temperature);

    public boolean update(String id, String u_name, String c_name, String sugar, String temperature);

    public boolean delete(String id);

    public ArrayList<Map<String, String>> selectAll();

    public Map<String, String> selectById(String id);
    
}
