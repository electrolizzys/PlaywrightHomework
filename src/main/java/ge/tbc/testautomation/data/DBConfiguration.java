package ge.tbc.testautomation.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfiguration {
    private static final Properties props = new Properties();

    static {
        try (InputStream inputStream = DBConfiguration.class.getClassLoader().getResourceAsStream("db.properties")){
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getURL(){
        return props.getProperty("db.url");
    }

    public static String getUsername(){
        return props.getProperty("db.username");
    }

    public static String getPassword(){
        return props.getProperty("db.password");
    }
}