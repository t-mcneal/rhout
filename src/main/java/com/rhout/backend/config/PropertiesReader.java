package com.rhout.backend.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesReader {

    private PropertiesReader() {}

    public static Properties readFile(String filename) {
        FileInputStream fis;
        Properties prop = new Properties();
        try {
            fis = new FileInputStream(filename);
            prop.load(fis);
            fis.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
