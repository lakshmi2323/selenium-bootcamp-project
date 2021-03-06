package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtilLibrary {
    /* The Properties class represents a persistent set of properties. The Properties can be saved to
    a stream or loaded from a stream. Each key and its corresponding value in the property list is a
    string. */
    Properties properties = new Properties();
    InputStream inputStreamConfig = null;

    //path of properties file which holds the information of the config file path
    public static String filePath=System.getProperty("user.dir")+"//config.properties";

   /* Reads a property list (key and element pairs) from the input character stream in a simple
   line-oriented format.*/
    public FileUtilLibrary() {
        loadProperties();
    }

    private void loadProperties() {
        try {
            inputStreamConfig = new FileInputStream(filePath);
            properties.load(inputStreamConfig);
        } catch (IOException e) {
            System.out.println("Error in loading configuration settings due to: \n");
            e.printStackTrace();
        }
    }
    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
