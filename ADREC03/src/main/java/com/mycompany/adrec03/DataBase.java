/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adrec03;

/**
 *
 * @author Belén
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    public Connection getConnection() {
        return connection;
    }

    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private String db= "coronavirus.db";    

    public DataBase()
    {
      try{
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:" + this.db );
         System.out.println("Conectado a la base de datos SQLite [ " + this.db + "]");
      }catch(ClassNotFoundException | SQLException e){
         System.out.println(e);
      }
    }
public boolean insert(String tabla, String campos, String valores)
    {
        boolean res=false;
        String q=" INSERT OR REPLACE INTO " + tabla + " ( " + campos + " ) VALUES ("+ valores +")";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.out.println(e);
        }
      return res;
    }

 public boolean creartabla()
    {
        boolean res=false;
        //Se arma la consulta
        String q="CREATE TABLE IF NOT EXISTS paises (\n" +"  dateRep varchar(50),\n" +
"  countriesAndTerritories varchar(50),\n" +
"  deaths INT(20),\n" +
"  cases INT(20),\n" +"  continentExp varchar(50),\n" +"PRIMARY KEY(dateRep,countriesAndTerritories))";
        //se ejecuta la consulta
        try {
            PreparedStatement pstm = getConnection().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.out.println(e);
        }
      return res;
    }

 public String select(String sel,int a)
 {
    String res="";
     try {
      statement = connection.createStatement();
      resultSet = statement.executeQuery(sel);
      while (resultSet.next())
      {
        if (a==2){  
        res+=resultSet.getString(1) + " | " + resultSet.getString(2)+ " \n";
      }
        else{
            res+=resultSet.getString(1) + " | " + resultSet.getString(2)+ " | " +resultSet.getString(3)+ " \n";
        }
     }
     }catch (SQLException ex) {
        System.out.println(ex);
     }
    return res;
 }
 
  public void desconectar()
    {
        try {
            getConnection().close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}