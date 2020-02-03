/*
 * @Denisenko Artur
 */

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

public class GetMappingPropertiesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandManager.class);

    private static GetMappingPropertiesUtil instance = null;
    private static String propertiesMappingFileName = "periodicals_mapping.properties";
    private Properties properties;

    private GetMappingPropertiesUtil() {
        LOGGER.info("GET PROPERTIES CLASS INITIALIZING");

        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesMappingFileName);

        try {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                LOGGER.error("MAPPING PROPERTIES FILE ISN'T FOUND IN CLASSPATH");
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static synchronized GetMappingPropertiesUtil getInstance() {
        if (instance == null) {
            instance = new GetMappingPropertiesUtil();
        }

        return instance;
    }

    public String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}
