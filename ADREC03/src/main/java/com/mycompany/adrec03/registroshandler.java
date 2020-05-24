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

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class registroshandler extends DefaultHandler { 
        boolean year,month,day = false;
	boolean cases = false;
	boolean countriesAndTerritories = false;
        boolean deaths =false;
        boolean continentExp=false;
        String syear,smonth,sday;
        Registros reg = new Registros();
        List<Registros> lista = new ArrayList<>();
    
        @Override
	public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("year")) {
			year = true;
		}
                if (qName.equalsIgnoreCase("month")) {
			month = true;
		}
                if (qName.equalsIgnoreCase("day")) {
			day = true;
		}

		if (qName.equalsIgnoreCase("cases")) {
			cases = true;
		}

		if (qName.equalsIgnoreCase("countriesAndTerritories")) {
			countriesAndTerritories = true;
		}
                if (qName.equalsIgnoreCase("deaths")) {
			deaths = true;
		}
                if (qName.equalsIgnoreCase("continentExp")) {
			continentExp = true;
		}
	}
        @Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (year) {
                        reg.setYear(new String(ch, start, length));
			year = false;                        
		}
                if (month) {
                        reg.setMonth(new String(ch, start, length));
			month = false;                        
		}
                if (day){
                    reg.setDay(new String(ch, start, length));
			day = false;  
                }
		if (cases) {
			reg.setCases(Integer.parseInt(new String(ch, start, length)));
			cases = false;
		}

		if (countriesAndTerritories) {
			reg.setCountriesAndTerritories(new String(ch, start, length));
			countriesAndTerritories = false;
		}
                if (deaths) {
			reg.setDeaths(Integer.parseInt(new String(ch, start, length)));
			deaths = false;
		}
                if (continentExp) {
			reg.setContinentExp(new String(ch, start, length));
			continentExp = false;
		}
	}
        @Override
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
                if (qName.equals("record")) {
            lista.add(reg);
            reg = new Registros();

	}
        }
        public List<Registros> getLista(){
        return lista;
        }

}