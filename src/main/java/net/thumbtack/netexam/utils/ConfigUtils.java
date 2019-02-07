package net.thumbtack.netexam.utils;


import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class ConfigUtils {
    private static Configuration config;

    static {
        Parameters params = new Parameters();
        File propertiesFile = new File("conf/config.properties");
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.fileBased()
                                .setFile(propertiesFile));

        try {
            config = builder.getConfiguration();
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getString(String name) {
        return config.getString(name);
    }

    public static int getInt(String name) {
        return config.getInt(name);
    }

    public static String[] getStringArray(String name) {
        return config.getStringArray(name);
    }
}
