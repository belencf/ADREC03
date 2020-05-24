/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.adrec03;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Belén
 */
public class main {

    public static void main(String[] args) throws IOException {
        String sbase = "coronavirus.db";
        File base = new File(sbase);
        boolean existe=false;
        if (base.exists()){
            existe=true;
        }
        DataBase bd = new DataBase();
        if (existe==false){    
        Boolean b=bd.creartabla();
        System.out.println("Creo tabla");
        }
        XML fichero=new XML();
        List<Registros> lista=fichero.parsear("coronavirus.xml");
       try {
        bd.getConnection().setAutoCommit(false);
        lista.forEach((reg) -> {
            bd.insert("paises", "dateRep, cases, countriesAndTerritories, deaths, continentExp", "'"+reg.getDateRep()+"', "+reg.getCases()+", '"+reg.getCountriesAndTerritories()+"', "+reg.getDeaths()+", '"+reg.getContinentExp()+"'");
            }); 
        bd.getConnection().commit();
        Scanner reader = new Scanner(System.in);
        boolean sair = false;
        int opcion; 
 
        while (!sair) {
 
            System.out.println("1. Obter aqueles paises con un número determinado de casos totais maior ao número proporcionado.");
            System.out.println("2. Obter para cada pais o maior número de mortes nun día. Ademais deberase indicar cal foi ese día");
            System.out.println("3. O pais con maior número de casos para cada día do que se teña información");
            System.out.println("4. Sair");
 
            try {
 
                System.out.println("Elixe unha opción");
                opcion = reader.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out .println("Escribe un numero");
                        int a = reader.nextInt();
                        System.out.println(bd.select("SELECT countriesAndTerritories,SUM(cases) FROM paises GROUP BY countriesAndTerritories HAVING SUM(cases)>"+a,2));
                        break;
                    case 2:
                        System.out.println(bd.select("SELECT countriesAndTerritories,MAX(deaths),dateRep FROM paises GROUP BY countriesAndTerritories",3));
                        break;
                    case 3:
                        System.out.println("Estamos descargando o ficheiro, espera uns segundos,por favor");
                        LeerXML lector=new LeerXML();
                        lista=lector.LeerXML();
                        try {
                            bd.getConnection().setAutoCommit(false);
                            lista.forEach((reg) -> {
                            bd.insert("paises", "dateRep, cases, countriesAndTerritories, deaths, continentExp", "'"+reg.getDateRep()+"', "+reg.getCases()+", '"+reg.getCountriesAndTerritories()+"', "+reg.getDeaths()+", '"+reg.getContinentExp()+"'");
                            }); 
                            bd.getConnection().commit();
                        } catch(SQLException  ex){
                            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(bd.select("SELECT countriesAndTerritories,cases,dateRep FROM paises GROUP BY dateRep HAVING MAX(cases) ORDER BY date(dateRep)",3));
                        break;
                    case 4:
                        sair = true;
                        
                        break;
                    default:
                        System.out.println("Non escolliches unha opción correcta");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                reader.next();
            }
        }                  
        bd.desconectar();
        } catch(SQLException  ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
