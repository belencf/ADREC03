/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adrec03;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Belén
 */
public class XML {
   
    List<Registros> lista;
    public List<Registros> parsear(String file){
      try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
        registroshandler handler =new registroshandler();
        saxParser.parse(file, handler);
        lista = handler.getLista();
        
    } catch(IOException | SAXException | ParserConfigurationException   ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
      return lista;
     
}
}
