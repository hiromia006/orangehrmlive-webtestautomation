package com.orangehrmlive.web.fluentlenium.util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum SupportedWebDriver {
    REMOTE("remote", false) {
        @Override
        public Capabilities getCapabilities(Device device) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            FirefoxOptions firefoxOptions = firefoxOptions(device);
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("--whitelisted-ips");
            firefoxOptions.addArguments("--disable-gpu");
            firefoxOptions.addArguments("--disable-extensions");
            firefoxOptions.addArguments("--no-sandbox");
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
            return capabilities;
        }
    },
    CHROME("chrome", false) {
        @Override
        public Capabilities getCapabilities(Device device) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions(device));
            return capabilities;
        }
    },
    FIREFOX("firefox", false) {
        @Override
        public Capabilities getCapabilities(Device device) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-width", Integer.toString(device.getScreenSize().width), "-height", Integer.toString(device.getScreenSize().height));
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
            return capabilities;
        }
    };

    private String name;
    private boolean windowResizeRequired;

    SupportedWebDriver(String name, boolean windowResizeRequired) {
        this.name = name;
        this.windowResizeRequired = windowResizeRequired;
    }

    public String getName() {
        return name;
    }

    public boolean isWindowResizeRequired() {
        return windowResizeRequired;
    }

    public abstract Capabilities getCapabilities(Device device);

    public static Optional<SupportedWebDriver> find(String name) {
        return Arrays.stream(SupportedWebDriver.values())
                .filter(driver -> driver.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    protected ChromeOptions chromeOptions(Device device) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (device.isMobile()) {
            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", device.getScreenSize().width);
            deviceMetrics.put("height", device.getScreenSize().height);
            deviceMetrics.put("pixelRatio", device.getPixelRatio());

            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", device.getUserAgent());
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        }

        chromeOptions.addArguments("window-size=" + device.getScreenSize().width + "," + device.getScreenSize().height);

        return chromeOptions;
    }

    protected FirefoxOptions firefoxOptions(Device device) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (device.isMobile()) {
            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", device.getScreenSize().width);
            deviceMetrics.put("height", device.getScreenSize().height);
            deviceMetrics.put("pixelRatio", device.getPixelRatio());

            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", device.getUserAgent());
            firefoxOptions.setCapability("mobileEmulation", mobileEmulation);
        }

        firefoxOptions.addArguments("window-size=" + device.getScreenSize().width + "," + device.getScreenSize().height);

        return firefoxOptions;
    }
}
