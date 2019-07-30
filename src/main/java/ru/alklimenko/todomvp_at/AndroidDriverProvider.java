package ru.alklimenko.todomvp_at;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class AndroidDriverProvider implements WebDriverProvider {

    public WebDriver createDriver(DesiredCapabilities capabilities) {
        String deviceName = System.getProperty("deviceName");
        String appiumUrl = System.getProperty("appiumUrl");
        System.out.println("Device: " + deviceName);
        System.out.println("Appium: " + appiumUrl);

        File app = getApk();
        capabilities.setPlatform(Platform.ANDROID);
        capabilities.setCapability("device","Android");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");

        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("app", app.getAbsolutePath());
        WebDriver driver;
        try {
            driver = new RemoteWebDriver(new URL(appiumUrl), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return driver;
    }

    //TODO : Здесь нужно описать логику получения apk
    private File getApk() {
        File apk = new File(System.getProperty("user.dir") + "/Test_app.apk");
        if (!apk.exists()) {
            String url = "http://address_apk";
            try (InputStream in = new URL(url).openStream()) {
                copyInputStreamToFile(in, apk);
            } catch (IOException e) {
                throw new AssertionError("Failed to download apk", e);
            }
        }
        return apk;
    }
}
