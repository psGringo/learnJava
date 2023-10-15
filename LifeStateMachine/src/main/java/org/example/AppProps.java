package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProps {

    private static Properties props;

    static {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + Constants.propsFileName;
        var props = new Properties();
        try {
            props.load(new FileInputStream(appConfigPath));
            AppProps.props = props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getMaxScreenX() {
        return getInt(Constants.screenSizeX);
    }

    public static int getMaxScreenY() {
        return getInt(Constants.screenSizeY);
    }

    public static Object get(String key) {
        return props.get(key);
    }

    public static Integer getInt(String key) {
        var s = String.valueOf(AppProps.get(key));
        Integer res = (Integer.parseInt(s));
        return res;
    }

}
