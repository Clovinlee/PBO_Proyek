/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo_proyek;

/**
 *
 * @author chris
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class DB {
     private static Connection c;
    
    public static void init(){
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/project_pbo";
            Connection c = DriverManager.getConnection(url,"root","");
            DB.c = c;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    
    public static ArrayList<String[]> query(String sql){
        return DB.query(sql,null);
    }
    
    public static ArrayList<String[]> query(String sql, Object[] data){
        ArrayList<String[]> resultData = new ArrayList<>();
        if(data == null){
            try {
                Statement s = DB.c.createStatement();
                //DataSet
               ResultSet rs = s.executeQuery(sql);
                
                ResultSetMetaData md = rs.getMetaData();
                int totalColumn = md.getColumnCount();
                
                while(rs.next()){
                    //Idx mulai dari 1 sampe n
                    String[] temp = new String[totalColumn];
                    for(int i = 1; i <= totalColumn; i++){
                         temp[i-1] = String.valueOf(rs.getObject(i));
                    }
                    resultData.add(temp);
                }
                
            } catch (Exception ex) {
                
            }
        }else{
            try {
                // Contoh Prepared Statement
                // insert into posts(title, body) VALUES(?, ?)
                // new Object[] {"Judul","asdasd"}
                PreparedStatement s = DB.c.prepareStatement(sql);
                for (int i = 0; i < data.length; i++) {
                    s.setObject(i+1, data[i]);
                }
                ResultSet rs = s.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                int totalColumn = md.getColumnCount();
                
                while(rs.next()){
                    //Idx mulai dari 1 sampe n
                    String[] temp = new String[totalColumn];
                    for(int i = 1; i <= totalColumn; i++){
                        temp[i-1] = String.valueOf(rs.getObject(i));
                    }
                    resultData.add(temp);
                }
                
            } catch (Exception ex) {
                
            }
        }
        return resultData;
    }
    
    public static boolean insert(String sql, Object[] data){
        boolean check = false;
        
        try{
            PreparedStatement s = DB.c.prepareStatement(sql);
            for(int i = 0; i < data.length; i++){
                s.setObject(i+1, data[i]);
            }
            check = s.execute();
        }catch(Exception ex){
            
        }
        check = true;
        
        return check;
    }
    
    public static int update(String sql, Object[] data) {
        int affectedRow = 0;
        try {
            PreparedStatement s = DB.c.prepareStatement(sql);
            for (int i = 0; i < data.length; i++) {
                s.setObject(i+1, data[i]);
            }
            affectedRow = s.executeUpdate();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        return affectedRow;
    }
    
    public static int delete(String sql, Object[] data) {
        return DB.update(sql, data);
    }
    
    public static void close(){
        try {
            DB.c.close();
        } catch (SQLException ex) {
        }
    }
}
