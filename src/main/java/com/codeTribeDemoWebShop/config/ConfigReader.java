package com.codeTribeDemoWebShop.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = load();

    private ConfigReader() {
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config.properties", e);
        }
        return properties;
    }

    public static String baseUrl() {
        return PROPERTIES.getProperty("baseUrl");
    }

    public static String browser() {
        return PROPERTIES.getProperty("browser");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(PROPERTIES.getProperty("headless"));
    }

    public static int explicitWait() {
        return Integer.parseInt(PROPERTIES.getProperty("explicitWaitSeconds"));
    }
}
