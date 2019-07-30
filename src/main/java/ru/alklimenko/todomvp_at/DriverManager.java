package ru.alklimenko.todomvp_at;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.alklimenko.todomvp_at.exceptions.AtDriverException;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager {
    private static Map<Long, WebDriver> driverMap = new ConcurrentHashMap<>();

    private static <T extends WebDriverProvider> WebDriver createDriver(Class<T> tClass) {
        try {
            T t = tClass.getConstructor().newInstance();
            return t.createDriver(new DesiredCapabilities());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new AtDriverException("Driver initialization error!", e);
        }
    }

    public static <T extends WebDriverProvider> void addDriver(Class<T> tClass) {
        removeDriver();
        Long id = Thread.currentThread().getId();
        driverMap.put(id, createDriver(tClass));
    }

    public static WebDriver getDriver() {
        Long id = Thread.currentThread().getId();
        if (!driverMap.containsKey(id)) {
            throw new AtDriverException("Driver not found for current thread!");
        }
        return driverMap.get(id);
    }

    public static void removeDriver() {
        Long id = Thread.currentThread().getId();
        if (driverMap.containsKey(id)) {
            driverMap.get(id).quit();
            driverMap.remove(id);
        }
    }
}
