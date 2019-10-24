/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plantasolar.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Braiam
 */
public class Utils {
     private static Properties configProperties;

    public static void loadProperties() {
        try {
            configProperties = new Properties();
            InputStream st = Thread.currentThread().getContextClassLoader().getResourceAsStream("PlantaSolar.properties");

            try {
                configProperties.load(st);
            } catch (IOException e) {
                
            }
            finally{
                st.close();
            }
        } catch (Throwable ex) {
            
        }
    }
    
        public static String getConfig(String key) {
       // if (configProperties == null) {
            loadProperties();
        //}
        return configProperties.getProperty(key);
    }
}
