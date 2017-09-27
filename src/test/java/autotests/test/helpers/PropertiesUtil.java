package autotests.test.helpers;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properties = new Properties();

    public static String getPropertyAsString(String key) {
        return properties.getProperty(key);
    }

    public PropertiesUtil(String env) {
        InputStream input = null;
        try {
            String filename = "properties/" + env + ".properties";
            input = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }
            //load a properties file from class path, inside static method
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
