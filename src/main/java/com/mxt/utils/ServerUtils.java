package com.mxt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerUtils {
    private static String host=null;
    private static String port=null;
    static {
        InputStream is = ServerUtils.class.getClassLoader().getResourceAsStream("serverConfig.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
             host = properties.getProperty("host");
             port = properties.getProperty("port");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHost(){
        return host;
    }

    public static String getPort(){
        return port;
    }

}
