package com.orangehrmlive.web.fluentlenium.test;


import com.orangehrmlive.web.fluentlenium.util.*;
import org.apache.commons.lang3.StringUtils;
import org.fluentlenium.adapter.testng.FluentTestNg;
import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public abstract class BaseTest extends FluentTestNg {
    private static final String KEY_CREDENTIALS_FILE = "external.credentials.file";
    private static final String DEFAULT_CREDENTIALS_FILE = "credentials.properties";

    private static final String KEY_SUFFIX_ALIAS = ".alias";
    private static final String KEY_SUFFIX_BASE_URL = ".baseUrl";
    private static final String KEY_SUFFIX_USERNAME = ".username";
    private static final String KEY_SUFFIX_PASSWORD = ".password";


    private static final String DEFAULT_CREDENTIAL = "default";

    private Device targetDeviceFromAnnotation;


    protected Credential loadCredential(String name) {
        CredentialStore store = CredentialStore.instance();
        if (store.isNotPopulated()) {
            populateCredentialStore();
        }
        if (store.has(name + KEY_SUFFIX_ALIAS)) {
            String alias = store.get(name + KEY_SUFFIX_ALIAS);
            if (StringUtils.isNotBlank(alias)) {
                return loadCredential(alias);
            }
        }

        String baseUrl = store.get(name + KEY_SUFFIX_BASE_URL);
        String username = store.get(name + KEY_SUFFIX_USERNAME);
        String password = store.get(name + KEY_SUFFIX_PASSWORD);
        if (StringUtils.isNotBlank(baseUrl)) {
            return new Credential(baseUrl, username, password);
        }
        throw new CredentialException("Credential with name: '" + name + "' not found");
    }


    protected Credential defaultCredential() {
        return loadCredential(DEFAULT_CREDENTIAL);
    }

    private void populateCredentialStore() {
        try {
            Properties properties = new Properties();
            properties.load(credentialsInputStream());
            CredentialStore store = CredentialStore.instance();
            properties.forEach((key, value) -> store.put(key.toString(), value.toString()));
            store.setPopulated(true);
        } catch (IOException e) {
            throw new CredentialException("Unable to load credentials from file", e);
        }
    }


    private InputStream credentialsInputStream() throws FileNotFoundException {
        String externalCredentialsPath = System.getProperty(KEY_CREDENTIALS_FILE);
        if (StringUtils.isNotBlank(externalCredentialsPath)) {
            File externalCredentialsFile = new File(externalCredentialsPath);
            if (externalCredentialsFile.exists()) {
                return new FileInputStream(externalCredentialsFile);
            } else {
                throw new CredentialException("Invalid credentials file path");
            }
        } else {
            return Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_CREDENTIALS_FILE);
        }
    }

    public <P extends FluentPage> P goTo(P page, String baseUrl) {
        if (StringUtils.isNotBlank(page.getUrl())) {
            super.setBaseUrl(baseUrl);
        }
        return goTo(page);
    }

    public <P extends FluentPage> P goTo(Class<P> pageClass, String baseUrl) {
        return goTo(newInstance(pageClass), baseUrl);
    }

    public boolean isAjaxComplete() {
        return JavaScriptUtils.isAjaxComplete(this);
    }


    private Device getTargetDevice() {
        if (targetDeviceFromAnnotation != null) {
            return targetDeviceFromAnnotation;
        }
        return Device.DESKTOP;
    }

    @Override
    public Capabilities getCapabilities() {
        return SupportedWebDriver.find(getWebDriver())
                .map(driver -> driver.getCapabilities(getTargetDevice()))
                .orElseGet(super::getCapabilities);
    }


    @Override
    public WebDriver newWebDriver() {
        WebDriver webDriver = super.newWebDriver();
        boolean shouldResizeWindow = SupportedWebDriver.find(getWebDriver())
                .map(SupportedWebDriver::isWindowResizeRequired)
                .orElse(false);
        if (shouldResizeWindow) {
            webDriver.manage().window().setSize(getTargetDevice().getScreenSize());
        }
        return webDriver;
    }


    @Override
    protected void starting(Class<?> testClass, String testName) {
        targetDeviceFromAnnotation = Arrays.stream(testClass.getMethods())
                .filter(method -> method.getName().equals(testName))
                .map(method -> method.getAnnotation(TargetDevice.class))
                .filter(Objects::nonNull)
                .map(TargetDevice::value)
                .findFirst()
                .orElseGet(() -> Optional.ofNullable(testClass.getAnnotation(TargetDevice.class))
                        .map(TargetDevice::value)
                        .orElse(null)
                );
        super.starting(testClass, testName);
        targetDeviceFromAnnotation = null;
    }
}
