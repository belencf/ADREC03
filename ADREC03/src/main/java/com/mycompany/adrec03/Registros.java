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
public class Registros {
   
    private String dateRep;
    private int cases;
    private String countriesAndTerritories;
    private int deaths;
    private String continentExp;
    private String year;
    private String month;
    private String day;
    /**
     * @return the dateRep
     */
    public String getDateRep() {
        if (Integer.parseInt(month)<10){
            this.month="0"+month;
        }
        if (Integer.parseInt(day)<10){
            this.day="0"+day;
        }
        dateRep=year+"-"+month+"-"+day;
        return dateRep;
    }

    /**
     * @param dateRep the dateRep to set
     */
    public void setDateRep(String dateRep) {
        this.dateRep = dateRep;
    }

    /**
     * @return the cases
     */
    public int getCases() {
        return cases;
    }

    /**
     * @param cases the cases to set
     */
    public void setCases(int cases) {
        this.cases = cases;
    }

    /**
     * @return the countriesAndTerritories
     */
    public String getCountriesAndTerritories() {
        return countriesAndTerritories;
    }

    /**
     * @param countriesAndTerritories the countriesAndTerritories to set
     */
    public void setCountriesAndTerritories(String countriesAndTerritories) {
        this.countriesAndTerritories = countriesAndTerritories;
    }

    /**
     * @return the deaths
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * @param deaths the deaths to set
     */
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    /**
     * @return the continentExp
     */
    public String getContinentExp() {
        return continentExp;
    }

    /**
     * @param continentExp the continentExp to set
     */
    public void setContinentExp(String continentExp) {
        this.continentExp = continentExp;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }
    
}
