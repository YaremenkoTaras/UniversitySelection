package com.epam.autum.selection.database;

import java.util.Properties;
import java.util.ResourceBundle;

public class DatabaseProperties {

    private static DatabaseProperties instance;
    private Properties properties;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "database";

    public static final String DRIVER = "driver";
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String AUTO_RECONNECT = "autoReconnect";
    public static final String CHAR_ENCODING = "characterEncoding";
    public static final String USE_UNICODE = "useUnicode";
    public static final String POOL_SIZE = "poolsize";
    public static final String USE_SSL = "useSSL";

    private DatabaseProperties() {
    }

    public static DatabaseProperties getInstance() {
        if (instance == null) {
            instance = new DatabaseProperties();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
            instance.setProperties();
        }
        return instance;
    }

    public Properties getProperties(){
        return this.properties;
    }

    public int getPoolSize(){
        int size = 1;
        try {
            size = Integer.parseInt(resource.getString(POOL_SIZE));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return size;
    }

    public String getURL(){
        return resource.getString(URL);
    }

    public String getDriver(){
        return resource.getString(DRIVER);
    }

    private void setProperties (){
        String user = resource.getString(USER);
        String pass = resource.getString(PASSWORD);
        String autoReconnect = resource.getString(AUTO_RECONNECT);
        String charEncoding = resource.getString(CHAR_ENCODING);
        String useUnicode = resource.getString(USE_UNICODE);
        String useSSL = resource.getString(USE_SSL);

        this.properties = new Properties();
        properties.put(USER, user);
        properties.put(PASSWORD, pass);
        properties.put(AUTO_RECONNECT, autoReconnect);
        properties.put(CHAR_ENCODING, charEncoding);
        properties.put(USE_UNICODE, useUnicode);
        properties.put(USE_SSL, useSSL);
    }

    public String getProperty(String key) {
        return resource.getString(key);
    }
}
