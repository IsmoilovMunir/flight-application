package by.munir.jdbc.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();

    }
    public static String get(String key){
       return PROPERTIES.getProperty(key);
    }
    private static void loadProperties(){
        try (var inputSteam = PropertiesUtil.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputSteam);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private PropertiesUtil(){
    }
}
