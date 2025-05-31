package com.tuan.vtube.common;

import java.util.ResourceBundle;

public class CommonUtil {
    /**
     * Get value from application.yml file.
     *
     * @param key - key of the value.
     * @return value of the key.
     */
    public static String getApplicationConfig(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("application");
        return rb.getString(key);
    }

    /**
     * Get value from config.properties file.
     *
     * @param key - key of the value.
     * @return value of the key.
     */
    public static String getConfig(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        return rb.getString(key);
    }
}
