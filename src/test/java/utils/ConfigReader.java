package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
   
	// METHOD TO READ FROM THE config.properties FILE
	private static Properties props = new Properties();
    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("config.properties not laoded", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
