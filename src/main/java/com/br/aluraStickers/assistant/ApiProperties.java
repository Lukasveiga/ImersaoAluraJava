package com.br.aluraStickers.assistant;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiProperties {

    // Getting api properties by Key
    public static String getApiProperties(String key) {

        String value;

        try (InputStream input = new FileInputStream("src/main/resources/aplication-properties.properties")) {

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            value = prop.getProperty(key);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return value;
    }


}
