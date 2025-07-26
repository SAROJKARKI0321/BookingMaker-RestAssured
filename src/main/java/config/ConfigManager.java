package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private  static ConfigManager instance;
    private Properties properties;
    private String envirnoment;

    private ConfigManager(){
        loadProperties();
    }
    public static synchronized ConfigManager getInstance(){
        if(instance==null){
            instance=new ConfigManager();
        }
        return  instance;
    }



    private void loadProperties(){
        properties=new Properties();
        envirnoment=System.getProperty("env","test");
        String propertiesfile=String.format("config/%s.properties",envirnoment);

        try(InputStream input =getClass().getClassLoader().getResourceAsStream(propertiesfile)){
            if(input==null){
                throw new RuntimeException("Properties file not found in classpath");

            }
            properties.load(input);
        }catch (IOException e){
            throw  new RuntimeException("Error while reading config.properties file");
        }


    }
    public String getProperty(String key){
        String value=System.getProperty(key);
        if(value==null){
            value=properties.getProperty(key);
        }
        return value;
    }
}
