/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.util;

import com.epam.command.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertiesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandManager.class);

    private static GetPropertiesUtil instance = null;
    private static String propertiesFileName = "periodicals_mapping.properties";
    private Properties properties;

    private GetPropertiesUtil() {
        LOGGER.info("GET PROPERTIES CLASS INITIALIZING");

        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);

        try {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                LOGGER.error("Mapping properties file  isn't found on the classpath");
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static synchronized GetPropertiesUtil getInstance() {
        if (instance == null) {
            instance = new GetPropertiesUtil();
        }

        return instance;
    }

    public String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}
